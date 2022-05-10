package com.bnc.main.shop;

import lombok.*;
import org.apache.logging.log4j.util.Strings;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

@Getter
@RequiredArgsConstructor
public class Shop {
    //상품번호,상품명,상품가격,
    private int sp_num;
    private String sp_name;
    private int sp_price;
    private String sp_category;
    private ShopSize sp_size = ShopSize.S; //S,M,L,XL,XXL

    public Shop(int sp_num, String sp_name, int sp_price, String sp_category, ShopSize sp_size) {
        checkArgument(Strings.isNotBlank(sp_name));
        checkArgument(Strings.isNotBlank(sp_category));

        this.sp_num = sp_num;
        this.sp_name = sp_name;
        this.sp_price = sp_price;
        this.sp_category = sp_category;
        this.sp_size = sp_size;

    }
}

