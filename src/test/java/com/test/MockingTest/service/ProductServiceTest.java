package com.test.MockingTest.service;

import com.test.MockingTest.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private final Map<Integer, Product> productIdToProductMapping = new HashMap<>();
    private final Product storedProduct = new Product(200, "Raymond Shirt", BigDecimal.valueOf(43.2), 12000);


    /*@BeforeEach
    void setUp() {
        productService = new ProductService(new HashMap<>());
    }*/

    @Test
    void storedProductThenFetchedBySameProductIdShouldReturnSameProductTest() {

        Product wallet = new Product(111, "Wallet", BigDecimal.valueOf(76.11), 1500);
        List<Product> products = productService.storeProducts(Arrays.asList(wallet));
        Mockito.verify(productService).storeProducts(Arrays.asList(wallet));
        /*
        Map<> Testing Whether it is being mapped correctly or not.
        */
        //Product productByProductId = productService.getProductByProductId(wallet.getId());
        //Assertions.assertEquals(productByProductId, wallet);

    }

    @Test
    void givenUnknownProductIdShouldReturnNull() {
        Integer unknownProductId = 1234;
        Mockito.lenient().when(productIdToProductMapping.get(anyInt())).thenReturn(null);
        Assertions.assertNull(productService.getProductByProductId(unknownProductId));
    }

    @Test
    void givenKnownProductIdShouldReturnNotNull() {
        Mockito.lenient().when(productIdToProductMapping.get(anyInt())).thenReturn(storedProduct);
        Assertions.assertNotNull(productService.getProductByProductId(storedProduct.getId()));
    }
}