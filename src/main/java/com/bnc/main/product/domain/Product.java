package com.bnc.main.product.domain;

import com.google.common.base.Preconditions;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;
import org.hibernate.annotations.Check;

import javax.persistence.Entity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Product extends BaseEntity{

    private String name;
    private int price;
    private String brand;

    public Product(String name, int price, String brand) {
        Preconditions.checkArgument(Strings.isNotBlank(name));
        Preconditions.checkArgument(Strings.isNotBlank(brand));

        this.name = name;
        this.price = price;
        this.brand = brand;
    }
}
