package org.backend.productcatalogsystem.Controllers;

import lombok.RequiredArgsConstructor;
import org.backend.productcatalogsystem.Models.Category;
import org.backend.productcatalogsystem.Services.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
}
