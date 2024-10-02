package org.backend.productcatalogsystem.Controllers;


import lombok.RequiredArgsConstructor;
import org.backend.productcatalogsystem.Models.SubCategory;
import org.backend.productcatalogsystem.Services.SubCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/subcategory")
@RequiredArgsConstructor
public class SubCategoryController {
    private final SubCategoryService subCategoryService;

    @PostMapping
    public ResponseEntity<?> createSubCategory(@RequestBody SubCategory subCategory) {
        return subCategoryService.saveSubCategory(subCategory);
    }
}
