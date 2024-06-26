package com.test.MockingTest.IntegrationTestCases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.MockingTest.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;

/*@AutoConfigureMockMvc
@ContextConfiguration(classes = {ProductConfigurationOnStartUp.class})*/
@WebMvcTest
@ComponentScan(basePackages = {"com.test.MockingTest"})
class ProductControllerTest {

    private MockMvc mockMvc;
    private final Product peter_eng_shirt = new Product(109, "Peter Eng Shirt", BigDecimal.valueOf(45), 15000);
    private final String PRODUCT_URI = "/api/v1/product";
    private final Product productToBeStored = new Product(200, "Raymond Shirt", BigDecimal.valueOf(43.2), 12000);

    @Autowired
    public ProductControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void storeProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.
                        post(PRODUCT_URI).contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(Arrays.asList(productToBeStored))))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    void givenProductIdShouldProvideBackThePreExistingProductTest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get(PRODUCT_URI)
                                .param("productId", String.valueOf(peter_eng_shirt.getId()))
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void givenEmptyListOfProductShouldReturnBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/product").
                queryParam("productId", "45678")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}