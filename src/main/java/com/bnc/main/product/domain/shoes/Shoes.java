package com.bnc.main.product.domain.shoes;

import com.bnc.main.product.domain.Product;

import javax.persistence.Entity;

@Entity
public class Shoes extends Product {

    private String shoesSize;
}
