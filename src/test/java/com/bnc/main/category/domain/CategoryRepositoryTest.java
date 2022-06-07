package com.bnc.main.category.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static com.bnc.main.category.controller.CreateCategoryDTO.ParentsCategoryCreateRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

@DataJpaTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void 카테고리_생성() {
        Category parentCategory1 = new Category(new ParentsCategoryCreateRequest("Clothes").getParentCategoryName());
        Category parentCategory2 = new Category(new ParentsCategoryCreateRequest("Bag").getParentCategoryName());
        Category parentCategory3 = new Category(new ParentsCategoryCreateRequest("Shoes").getParentCategoryName());
        Category parentCategory4 = new Category(new ParentsCategoryCreateRequest("Accessory").getParentCategoryName());

        categoryRepository.save(parentCategory1);
        categoryRepository.save(parentCategory2);
        categoryRepository.save(parentCategory3);
        categoryRepository.save(parentCategory4);

        Category childCategory1 = new Category("Top");
        Category childCategory2 = new Category("Pants");
        Category childCategory3 = new Category("Shirts");

        parentCategory1.addChildCategory(childCategory1);
        parentCategory1.addChildCategory(childCategory2);
        parentCategory1.addChildCategory(childCategory3);
    }

    @Test
    void 부모카테고리_하위요소_찾기_성공() {
        List<Category> childByParentId = categoryRepository.getChildByParentId(1L).orElseThrow();

        for (Category category : childByParentId) {
            assertThat(category.getParent().getId()).isEqualTo(1L);
        }
    }

    @Test
    void 부모카테고리_선택_성공() {
        List<Category> categories = categoryRepository.foundParentCategory().orElseThrow();

        for (Category category : categories) {
            assertThatNullPointerException().isThrownBy(() -> category.getParent().getId());
        }
    }

    @Test
    void 카테고리별_분류_성공() {
        List<Category> foundCategorys = categoryRepository.categoryClassification(1L).orElseThrow();

        for (Category foundCategory : foundCategorys) {
            assertThat(foundCategory.parent.getId()).isEqualTo(1L);
        }
    }
}
