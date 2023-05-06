package com.test.MockingTest.IntegrationTestCases;

import com.test.MockingTest.domain.Product;
import com.test.MockingTest.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class ProductServiceTest {
    private final Product productToBeStored = new Product(200, "Raymond Shirt", BigDecimal.valueOf(43.2), 12000);

    @InjectMocks
    private ProductService productService;
    @Mock
    private final Map<Integer, Product> productIdToProductMapping = new HashMap<>();

    @Test
    void givenProductToStoreShouldReturnSameProduct() {
        Mockito.lenient().when(productIdToProductMapping.get(Mockito.anyInt())).thenReturn(null);
        Assertions.assertNull(productService.getProductByProductId(productToBeStored.getId()));
        productService.storeProducts(Arrays.asList(productToBeStored));
        fetchProductByGivenProduct(productToBeStored);
    }

    @Test
    void givenProductIdShouldReturnProductSuccessfully() {
        fetchProductByGivenProduct(productToBeStored);
    }

    private void fetchProductByGivenProduct(Product productToBeStored) {
        Mockito.lenient().when(productIdToProductMapping.get(Mockito.anyInt())).thenReturn(productToBeStored);
        Assertions.assertNotNull(productService.getProductByProductId(productToBeStored.getId()));
    }
}