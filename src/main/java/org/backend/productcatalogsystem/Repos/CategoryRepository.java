package org.backend.productcatalogsystem.Repos;

import org.backend.productcatalogsystem.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
