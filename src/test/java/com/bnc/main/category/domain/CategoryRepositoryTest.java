package com.bnc.main.category.domain;

import com.bnc.main.product.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    @Transactional
    void 카테고리_생성_성공(){

        List<Product> tShirtList = new ArrayList<>();
        List<Product> roundShirtList = new ArrayList<>();
        List<Product> knitList = new ArrayList<>();

        Product blackT = new Product("blackT", 12000, "prada");

        tShirtList.add(blackT);
        roundShirtList.add(blackT);
        knitList.add(blackT);

        Category category = new Category("top");

        Category tShirt = new Category("tShirt", tShirtList);
        Category roundShirt = new Category("roundShirt", roundShirtList);
        Category knit = new Category("knit", knitList);

        category.addChildCategory(tShirt);
        category.addChildCategory(roundShirt);
        category.addChildCategory(knit);

        categoryRepository.save(category);

        Category foundCategory = categoryRepository.findById(category.getId()).orElseThrow();
        List<Category> foundChildCategory = categoryRepository.getChildByParentId(category.getId()).orElseThrow();

        assertThat(foundCategory).isEqualTo(category);
        assertThat(foundChildCategory).containsExactly(tShirt, roundShirt, knit);
    }
}
