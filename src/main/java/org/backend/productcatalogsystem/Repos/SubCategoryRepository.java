package org.backend.productcatalogsystem.Repos;

import org.backend.productcatalogsystem.Models.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {

        SubCategory findByName(String name);
}
