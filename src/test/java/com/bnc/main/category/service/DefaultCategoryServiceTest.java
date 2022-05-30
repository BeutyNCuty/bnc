package com.bnc.main.category.service;


import com.bnc.main.category.domain.Category;
import com.bnc.main.category.domain.CategoryRepository;
import com.bnc.main.testSupport.BaseServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static com.bnc.main.category.controller.CreateCategoryDTO.ChildCategoryCreateRequest;
import static com.bnc.main.category.controller.CreateCategoryDTO.ParentsCategoryCreateRequest;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class DefaultCategoryServiceTest{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @Test
    void 일차_카테고리생성_성공(){
        ParentsCategoryCreateRequest clothes = new ParentsCategoryCreateRequest("Clothes");

        Category parentsCategory = categoryService.createParentsCategory(clothes);

        assertThat(parentsCategory.getName()).isEqualTo("Clothes");
        assertThat(parentsCategory.getParent()).isEqualTo(null);
    }

    @Test
    void 이차_카테고리생성_성공(){
        ParentsCategoryCreateRequest clothes = new ParentsCategoryCreateRequest("Clothes");

        Category parentsCategory = categoryService.createParentsCategory(clothes);

        ChildCategoryCreateRequest newCategory = new ChildCategoryCreateRequest(parentsCategory.getId()+"" , "Shirts");

        Category secondCategory = categoryService.createSecondCategory(newCategory);

        List<Category> allCategory = categoryRepository.findAll();

        assertThat(allCategory).contains(secondCategory);
    }

    @Test
    void 카테고리_분류_성공(){
        Category parentCategory1 = new Category("Clothes");

        Category parentCategory2 = new Category("Bag");

        categoryRepository.save(parentCategory1);
        categoryRepository.save(parentCategory2);

        Category childCategory1 = new Category("Top");

        Category childCategory2 = new Category("Pants");

        Category childCategory3 = new Category("Backpack");

        parentCategory1.addChildCategory(childCategory1);
        parentCategory1.addChildCategory(childCategory2);
        parentCategory2.addChildCategory(childCategory3);

        List<Category> categories1 = categoryService.categoryClassification(parentCategory1.getId()).orElseThrow();

        List<Category> categories2 = categoryService.categoryClassification(parentCategory2.getId()).orElseThrow();

        assertThat(categories1).containsExactly(childCategory2,childCategory1);
        assertThat(categories2).containsExactly(childCategory3);
    }

    @Test
    void 카테고리_정보_변경_성공(){
        /*테스트미완성*/
        Category Clothes = categoryService.createParentsCategory(new ParentsCategoryCreateRequest("Clothes"));

        Category Bag = categoryService.createParentsCategory(new ParentsCategoryCreateRequest("Bag"));
    }
}
