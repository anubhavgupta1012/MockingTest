package com.test.MockingTest.IntegrationTestCases;

import com.test.MockingTest.domain.Product;
import com.test.MockingTest.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest
class ProductServiceTest {
    private final Product productToBeStored = new Product(200, "Raymond Shirt", BigDecimal.valueOf(43.2), 12000);

    @InjectMocks
    private ProductService productService;
    @Mock
    private final Map<Integer, Product> productIdToProductMapping = new HashMap<>();

    @Test
    void givenProductToStoreShouldReturnSameProductOnFetchById() {
        productService.storeProducts(Arrays.asList(productToBeStored));
        Map<Integer, Product> transformedProduct = Arrays.asList(productToBeStored).stream().collect(Collectors.toMap(Product::getId, p -> p));
        Mockito.verify(productIdToProductMapping).putAll(transformedProduct);
    }

    @Test
    void givenProductIdShouldReturnProductSuccessfully() {
        Mockito.lenient().when(productIdToProductMapping.get(ArgumentMatchers.anyInt())).thenReturn(productToBeStored);
        Product productByProductId = productService.getProductByProductId(productToBeStored.getId());
        Assertions.assertNotNull(productByProductId);
    }
}