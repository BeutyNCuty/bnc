package com.bnc.main.product.domain;

import com.bnc.main.support.BaseEntity;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

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
