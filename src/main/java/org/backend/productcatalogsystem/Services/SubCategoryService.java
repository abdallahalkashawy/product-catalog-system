package org.backend.productcatalogsystem.Services;

import lombok.AllArgsConstructor;
import org.backend.productcatalogsystem.Models.SubCategory;
import org.backend.productcatalogsystem.Repos.SubCategoryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public ResponseEntity<?> saveSubCategory(SubCategory subCategory) {
        SubCategory newSubCategory = subCategoryRepository.save(subCategory);
        return ResponseEntity.ok(newSubCategory);
    }
}
