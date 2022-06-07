package com.bnc.main.product.domain;

import com.bnc.main.category.domain.Category;
import com.bnc.main.product.controller.dto.ProductCreateDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

@Entity
@Getter
@NoArgsConstructor
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String name;

    private int price;

    private String brand;

    private int stock;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.CREATED;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    public Product(String name, int price, String brand){
        checkArgument(Strings.isNotBlank(name));
        checkArgument(Strings.isNotBlank(brand));

        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    public void change(String name, int price, String brand) {
        checkArgument(Strings.isNotBlank(name));
        checkArgument(Strings.isNotBlank(brand));

        this.name = name;
        this.price = price;
        this.brand = brand;
    }

    public void delete() {
        this.productStatus = ProductStatus.DELETED;
    }
}
