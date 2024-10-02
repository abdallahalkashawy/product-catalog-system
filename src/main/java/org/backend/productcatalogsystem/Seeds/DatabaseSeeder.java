package org.backend.productcatalogsystem.Seeds;

import org.backend.productcatalogsystem.Models.Category;
import org.backend.productcatalogsystem.Models.Product;
import org.backend.productcatalogsystem.Models.SubCategory;
import org.backend.productcatalogsystem.Repos.CategoryRepository;
import org.backend.productcatalogsystem.Repos.ProductRepository;
import org.backend.productcatalogsystem.Repos.SubCategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final CategoryRepository categoryRepository;

    public DatabaseSeeder(ProductRepository productRepository,
                          SubCategoryRepository subCategoryRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        seedCategories();
        seedSubCategories();
        seedProducts();
    }

    private void seedCategories() {
        // Check if categories already exist to avoid duplication
        if (categoryRepository.count() == 0) {
            List<Category> categories = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {  // Create 10 categories
                Category category = new Category();
                category.setName("Category " + i);
                categories.add(category);
            }
            categoryRepository.saveAll(categories);
        }
    }

    private void seedSubCategories() {
        // Check if subcategories already exist to avoid duplication
        if (subCategoryRepository.count() == 0) {
            List<Category> categories = categoryRepository.findAll();
            List<SubCategory> subCategories = new ArrayList<>();
            for (Category category : categories) {
                for (int j = 1; j <= 10; j++) {  // Create 10 subcategories for each category
                    SubCategory subCategory = new SubCategory();
                    subCategory.setName("SubCategory " + category.getName() + " " + j);
                    subCategory.setCategory(category);
                    subCategories.add(subCategory);
                }
            }
            subCategoryRepository.saveAll(subCategories);
        }
    }

    private void seedProducts() {
        // Check if products already exist to avoid duplication
        if (productRepository.count() == 0) {
            List<SubCategory> subCategories = subCategoryRepository.findAll();
            List<Product> products = new ArrayList<>();
            for (SubCategory subCategory : subCategories) {
                for (int k = 1; k <= 10; k++) {  // Create 10 products for each subcategory
                    Product product = new Product();
                    product.setName("Product " + subCategory.getName() + " " + k);
                    product.setDescription("Description for " + product.getName());
                    product.setSubCategory(subCategory);
                    products.add(product);
                }
            }
            productRepository.saveAll(products);
        }
    }

}
