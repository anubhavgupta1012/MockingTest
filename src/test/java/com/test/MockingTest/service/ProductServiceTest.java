package com.test.MockingTest.service;

import com.test.MockingTest.domain.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;

class ProductServiceTest {

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(new HashMap<>());
    }

    @Test
    void storedProductThenFetchedBySameProductIdShouldReturnSameProductTest() {
        Product wallet = new Product(111, "Wallet", BigDecimal.valueOf(76.11), 1500);
        productService.storeProducts(Arrays.asList(wallet));
        Assertions.assertEquals(productService.getProductByProductId(wallet.getId()), wallet);
    }

    @Test
    void givenUnknownProductIdShouldReturnNull() {
        Integer unknownProductId = 1234;
        Assertions.assertNull(productService.getProductByProductId(unknownProductId));
    }
}