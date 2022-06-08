package com.bnc.main.product.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
@Getter
@AllArgsConstructor
public class ProductCreateDTO {

    private String category;

    private String name;

    private int price;

    private String brand;

    private int stock;
}
