package com.bnc.main.product.domain;

import com.bnc.main.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;

import static com.google.common.base.Preconditions.checkArgument;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    private String name;
    private int price;
    private String brand;
    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus = ProductStatus.CREATED;

    public Product(String name, int price, String brand) {
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
