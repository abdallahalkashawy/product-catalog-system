package org.backend.productcatalogsystem.Repos;

import org.backend.productcatalogsystem.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);
}
