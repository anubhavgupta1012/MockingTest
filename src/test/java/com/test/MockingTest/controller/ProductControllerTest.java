package com.test.MockingTest.controller;

import com.test.MockingTest.domain.Product;
import com.test.MockingTest.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    Product peter_eng_shirt = new Product(109, "Peter Eng Shirt", BigDecimal.valueOf(45), 15000);

    @Test
    void nullProductListGivenShouldProvideBadRequestResponseBack() {
        Assertions.assertEquals(productController.storeProducts(null).getStatusCode(), HttpStatus.BAD_REQUEST);
    }


    @Test
    void emptyProductListGivenShouldProvideBadRequestResponseBack() {
        Assertions.assertEquals(productController.storeProducts(Collections.EMPTY_LIST).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void validProductListGivenShouldProvideOkResponseBack() {
        Assertions.assertEquals(productController.storeProducts(Arrays.asList(peter_eng_shirt)).getStatusCode(), HttpStatus.OK);
    }

    @Test
    void givenNullProductIdShouldReturnBadRequestResponseBack() {
        Assertions.assertEquals(productController.getProductByProductId(null).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    @Test
    void givenUnknownProductIdShouldReturnNullOnFetchById() {
        Integer unknownId = 9999999;
        Mockito.lenient().when(productService.getProductByProductId(anyInt())).thenReturn(null);
        Product fetchedShirt = productController.getProductByProductId(unknownId).getBody();
        Assertions.assertEquals(null, fetchedShirt);
    }
}