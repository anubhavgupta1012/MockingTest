package com.test.MockingTest.controller;

import com.test.MockingTest.domain.Product;
import com.test.MockingTest.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

@SpringBootTest
class ProductControllerTest {

    private ProductService productService;
    private ProductController productController;
    Product peter_eng_shirt = new Product(109, "Peter Eng Shirt", BigDecimal.valueOf(45), 15000);

    @BeforeEach
    void setUp() {
        this.productService = new ProductService(new HashMap<>());
        this.productController = new ProductController(productService);


    }

    @Test
    void nullProductListGivenShouldProvideBadRequestResponseBack() {
        Assertions.assertEquals(productController.storeProducts(null), ResponseEntity.badRequest().build());
    }


    @Test
    void emptyProductListGivenShouldProvideBadRequestResponseBack() {
        Assertions.assertEquals(productController.storeProducts(Collections.EMPTY_LIST), ResponseEntity.badRequest().build());
    }

    @Test
    void validProductListGivenShouldProvideOkResponseBack() {

        Assertions.assertEquals(productController.storeProducts(
                                Arrays.asList(peter_eng_shirt))
                        .getStatusCode(),
                HttpStatus.OK);
    }

    @Test
    void givenNullProductIdShouldReturnBadRequestResponseBack() {
        Assertions.assertEquals(productController.getProductByProductId(null), ResponseEntity.badRequest().build());
    }

    @Test
    void storedAnyProductAndFetchedSameProductByProductIdShouldReturnOkResponse() {
        HttpStatus statusCode = productController.storeProducts(Arrays.asList(peter_eng_shirt)).getStatusCode();
        if (statusCode.is2xxSuccessful()) {
            Product fetchedShirt = productController.getProductByProductId(peter_eng_shirt.getId()).getBody();
            Assertions.assertEquals(peter_eng_shirt, fetchedShirt);
        }
    }
}