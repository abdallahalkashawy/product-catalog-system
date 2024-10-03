package org.backend.productcatalogsystem.Services;


import lombok.AllArgsConstructor;
import org.backend.productcatalogsystem.Models.Product;
import org.backend.productcatalogsystem.Repos.ProductRepository;
import org.backend.productcatalogsystem.dtos.ProductDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;


    public ResponseEntity<?> createProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return ResponseEntity.ok(newProduct);
    }


    public ResponseEntity<?> getProductById(Long id) {
        if(id == null || id <= 0) {
            return ResponseEntity.badRequest().body("Invalid product id");
        }
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.badRequest().body("Product not found");
        }
        return ResponseEntity.ok(
                ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .subcategory_name(product.getSubCategory().getName())
                        .category_name(product.getSubCategory().getCategory().getName())
                        .build());
    }

   public ResponseEntity<?> getProductByName(String name) {

       Product product = productRepository.findByName(name);

       if (product == null) {
           return ResponseEntity.badRequest().body("Product not found.");
       }

       // Return the product and execution time
       return ResponseEntity.ok(
               ProductDTO.builder()
                       .id(product.getId())
                       .name(product.getName())
                       .description(product.getDescription())
                       .subcategory_name(product.getSubCategory().getName())
                       .category_name(product.getSubCategory().getCategory().getName())
                       .build());
    }
    public Set<ProductDTO> getAllProducts() {

        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .subcategory_name(product.getSubCategory().getName())
                .category_name(product.getSubCategory().getCategory().getName())
                .build()).collect(Collectors.toSet());
    }



}
