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
        //for Validation of incoming request
        if (products == null || products.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<Product> responseProduct = productService.storeProducts(products);
        return responseProduct != null ? ResponseEntity.ok(responseProduct) : ResponseEntity.badRequest().build();
    }


    @GetMapping("/product")
    public ResponseEntity<Product> getProductByProductId(@RequestParam Integer productId) {

        if (productId == null) return ResponseEntity.badRequest().build();

        Product productByProductId = productService.getProductByProductId(productId);
        return productByProductId != null ? ResponseEntity.ok(productByProductId) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/product")
    public ResponseEntity<Integer> deleteProductByProductId(@RequestParam Integer productId) {
        if (productId == null) return ResponseEntity.badRequest().build();
        productService.deleteProduct(productId);
        return ResponseEntity.ok(productId);
    }
}
