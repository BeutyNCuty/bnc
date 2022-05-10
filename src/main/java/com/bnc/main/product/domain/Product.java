package com.bnc.main.product.domain;

import com.bnc.main.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Column;
import javax.persistence.Entity;

import static com.google.common.base.Preconditions.*;

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
