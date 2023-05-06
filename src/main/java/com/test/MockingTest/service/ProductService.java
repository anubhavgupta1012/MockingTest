package com.test.MockingTest.service;

import com.test.MockingTest.domain.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final Map<Integer, Product> productIdToProductMapping;

    public ProductService(Map<Integer, Product> productIdToProductMapping) {
        this.productIdToProductMapping = productIdToProductMapping;
    }

    public void storeProducts(List<Product> products) {
        Map<Integer, Product> productsToBeStored = products.stream().collect(Collectors.toMap(Product::getId, p -> p));
        productIdToProductMapping.putAll(productsToBeStored);
    }

    public Product getProductByProductId(Integer productId) {
        return productIdToProductMapping.get(productId);
    }
}
