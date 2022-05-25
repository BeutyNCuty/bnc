package com.bnc.main.category.domain;

import com.bnc.main.product.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class CategoryTest {

    @Test
    void 카테고리_생성_성공(){
        List<Product> products = new ArrayList<>();

        final Product blackKnit = new Product("blackKnit", 30000, "lacoste");
        products.add(blackKnit);
        Category knit = new Category("knit", products);

        assertThat(knit.getName()).isEqualTo("knit");
        assertThat(knit.getProducts().get(0).getName()).isEqualTo("blackKnit");
        assertThat(knit.getProducts().get(0).getPrice()).isEqualTo(30000);
        assertThat(knit.getProducts().get(0).getBrand()).isEqualTo("lacoste");
    }

    @Test
    void 이차_카테고리_생성_성공(){

        List<Product> products = new ArrayList<>();

        final Product blackKnit = new Product("blackKnit", 30000, "lacoste");
        products.add(blackKnit);

        Category knit = new Category("knit", products);

        Category top = new Category("top");

        top.addChildCategory(knit);

        assertThat(top.getName()).isEqualTo("top");

        assertThat(top.getChild().get(0).getName()).isEqualTo("knit");
        assertThat(top.getChild().get(0).getParent().getName()).isEqualTo("top");

        assertThat(top.getChild().get(0).getProducts().get(0).getName()).isEqualTo("blackKnit");
        assertThat(top.getChild().get(0).getProducts().get(0).getPrice()).isEqualTo(30000);
        assertThat(top.getChild().get(0).getProducts().get(0).getBrand()).isEqualTo("lacoste");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 카테고리_이름이_null_이면_실패(String name){

        assertThatIllegalArgumentException().isThrownBy(() -> new Category(name));
    }

    @Test
    void 카테고리_이름이_공백이면_실패(){

        String name = "   ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Category(name));
    }
}
