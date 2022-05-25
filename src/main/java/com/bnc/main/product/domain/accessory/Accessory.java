package com.bnc.main.product.domain.accessory;

import com.bnc.main.product.domain.Product;

import javax.persistence.Entity;

@Entity
public class Accessory extends Product {
    private String accessory = "oneSize";
}
