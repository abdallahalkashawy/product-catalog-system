package org.backend.productcatalogsystem.Controllers;

import lombok.RequiredArgsConstructor;
import org.backend.productcatalogsystem.Models.Product;
import org.backend.productcatalogsystem.Services.ProductService;
import org.backend.productcatalogsystem.dtos.ProductDTO;
import org.backend.productcatalogsystem.dtos.ProductNameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }
    @GetMapping
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }

    @GetMapping("/name")
    public ResponseEntity<?> getProductByName(@RequestBody ProductNameRequest product) {
        return productService.getProductByName(product.getName());
    }


}
