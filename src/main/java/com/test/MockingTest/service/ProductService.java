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

    public List<Product> storeProducts(List<Product> products) {
        if (!isProductListValid(products)) return null;

        Map<Integer, Product> productsToBeStored = products.stream().collect(Collectors.toMap(Product::getId, p -> p));
        productIdToProductMapping.putAll(productsToBeStored);
        return products;
    }

    public Product getProductByProductId(Integer productId) {
        return productId != null ? productIdToProductMapping.get(productId) : null;
    }

    //for Defensive Programming
    private boolean isProductListValid(List<Product> products) {
        if (products == null || products.isEmpty()) return false;
        return true;
    }

    public void deleteProduct(Integer productId) {
        if (productId != null) {
            productIdToProductMapping.remove(productId);
        }
    }
}
