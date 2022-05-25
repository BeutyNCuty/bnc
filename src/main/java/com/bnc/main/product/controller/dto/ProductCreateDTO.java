package com.bnc.main.product.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@AllArgsConstructor
public class ProductCreateDTO {

    @NotEmpty(message = "카테고리 는 필수로 입력해야는 항목입니다.")
    private String category;
    @NotEmpty(message = "상품이름 은필수로 입력해야는 항목입니다.")
    private String name;
    @NotEmpty(message = "가격 은 필수로 입력해야는 항목입니다.")
    private int price;
    @NotEmpty(message = "브랜드 는 필수로 입력해야는 항목입니다.")
    private String brand;
    @NotEmpty(message = "재고 는 필수로 입력해야는 항목입니다.")
    private int stock;
}
