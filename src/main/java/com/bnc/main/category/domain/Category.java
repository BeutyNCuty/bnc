package com.bnc.main.category.domain;

import com.bnc.main.category.controller.CreateCategoryDTO;
import com.bnc.main.product.domain.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Category {

    @ManyToMany(cascade = ALL)
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    List<Product> products = new ArrayList<>();

    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "parent_id")
    Category parent;

    @Id
    @Column(name = "category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "parent", cascade = ALL)
    private List<Category> child = new ArrayList<>();

    public Category(String name) {
        checkArgument(Strings.isNotBlank(name));

        this.name = name;
    }

    public Category(String name, List<Product> products) {

        checkArgument(Strings.isNotBlank(name));

        this.name = name;
        this.products = products;
    }

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void updateCategory(CreateCategoryDTO.ChildCategoryCreateRequest selectCategory) {
        this.name = selectCategory.getChildCategoryName();
        this.parent.setId(Long.parseLong(selectCategory.getParentCategoryName()));
    }
}
