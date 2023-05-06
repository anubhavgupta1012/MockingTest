package com.test.MockingTest.controller;

import com.test.MockingTest.domain.Product;
import com.test.MockingTest.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<List<Product>> storeProducts(@RequestBody List<Product> products) {

        if (!isProductListValid(products)) {
            return ResponseEntity.badRequest().build();
        }
        productService.storeProducts(products);
        return ResponseEntity.ok(products);
    }


    @GetMapping("/product")
    public ResponseEntity<Product> getProductByProductId(@RequestParam Integer productId) {
        if (productId == null) {
            return ResponseEntity.badRequest().build();
        }

        Product productByProductId = productService.getProductByProductId(productId);
        return productByProductId != null ? ResponseEntity.ok(productByProductId) : ResponseEntity.notFound().build();
    }

    private boolean isProductListValid(List<Product> products) {
        if (products == null || products.isEmpty()) return false;
        return true;
    }
}
