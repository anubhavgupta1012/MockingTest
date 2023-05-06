package com.test.MockingTest.config;

import com.test.MockingTest.domain.Product;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class ProductConfigurationOnStartUp {

    @Bean
    public Map<Integer, Product> getProductIdToProductMappings() {
        Product nikeAir = new Product(101, "Nike Shoes", BigDecimal.valueOf(9.71), 10000);
        Product adidasCap = new Product(102, "Adidas Cap", BigDecimal.valueOf(2.33), 7000);
        Product joggers = new Product(107, "Nike Joggers", BigDecimal.valueOf(42.11), 12000);
        Product trouser = new Product(108, "Roadster Trouser", BigDecimal.valueOf(42.11), 3000);
        Product shirt = new Product(109, "Peter Eng Shirt", BigDecimal.valueOf(45), 15000);

        List<Product> products = Arrays.asList(nikeAir, adidasCap, joggers, trouser, shirt);
        Map<Integer, Product> productIdToProductMappings = products.stream().collect(Collectors.toMap(Product::getId, p -> p));
        return productIdToProductMappings;
    }
}
