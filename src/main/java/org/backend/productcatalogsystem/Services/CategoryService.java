package org.backend.productcatalogsystem.Services;

import lombok.AllArgsConstructor;
import org.backend.productcatalogsystem.Models.Category;
import org.backend.productcatalogsystem.Repos.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> createCategory(Category category) {
        Category newCategory = categoryRepository.save(category);
        return ResponseEntity.ok(newCategory);
    }
}
