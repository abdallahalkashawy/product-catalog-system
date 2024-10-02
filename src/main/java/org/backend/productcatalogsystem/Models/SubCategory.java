package org.backend.productcatalogsystem.Models;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "subCategory")
    private Set<Product> products;

}
