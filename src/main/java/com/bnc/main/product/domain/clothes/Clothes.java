package com.bnc.main.product.domain.clothes;

import com.bnc.main.product.domain.Product;
import lombok.Getter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@DiscriminatorValue("C")
public class Clothes extends Product {

    @Enumerated(EnumType.STRING)
    private ClothesSize clothesSize = ClothesSize.S;
}
