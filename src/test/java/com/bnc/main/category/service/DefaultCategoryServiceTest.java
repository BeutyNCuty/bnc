package com.bnc.main.category.service;


import com.bnc.main.category.controller.CreateCategoryDTO;
import com.bnc.main.category.domain.Category;
import com.bnc.main.category.domain.CategoryRepository;
import com.bnc.main.testSupport.BaseServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


class DefaultCategoryServiceTest extends BaseServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DefaultCategoryService defaultCategoryService;

    @Test
    void 이차_카테고리생성_성공(){
        CreateCategoryDTO.Info newCategory = new CreateCategoryDTO.Info("1" , "나시고랭");

        Category secondCategory = defaultCategoryService.createSecondCategory(newCategory);

        List<Category> allCategory = categoryRepository.findAll();

        Assertions.assertThat(allCategory.contains(secondCategory)).isEqualTo(true);
    }

    @Test
    void 카테고리_분류_성공(){

        List<Category> categories = defaultCategoryService.categoryClassification(1l).orElseThrow();

        for (Category category : categories) {
          Assertions.assertThat(category.getParent().getId()).isEqualTo(1l);
        }
    }

    @Test
    void 카테고리_정보_변경_성공(){

        CreateCategoryDTO.Info modifyCategory = new CreateCategoryDTO.Info("3", "안녕");

        defaultCategoryService.updateCategory(5l , modifyCategory);

        Category findById = categoryRepository.findById(5l).orElseThrow();

        Assertions.assertThat(findById.getName()).isEqualTo("안녕");
        Assertions.assertThat(findById.getParent().getId()).isEqualTo(3l);
    }
}
