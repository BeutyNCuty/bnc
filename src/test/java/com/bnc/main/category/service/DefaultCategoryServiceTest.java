package com.bnc.main.category.service;


import com.bnc.main.category.domain.Category;
import com.bnc.main.category.domain.CategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;

import static com.bnc.main.category.controller.CreateCategoryDTO.ChildCategoryCreateRequest;
import static com.bnc.main.category.controller.CreateCategoryDTO.ParentsCategoryCreateRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@Transactional
@SpringBootTest
class DefaultCategoryServiceTest{

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    private Category category1;
    private Category category2;
    private Category category3;
    private Category category4;
    
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

        List<Category> allFoundCategory = categoryRepository.findAll();

        assertThat(allFoundCategory).contains(parentsCategory,secondCategory);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 일차_카테고리_null이면_이차_카테고리생성_실패(String parntCategoryName){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> categoryService
                        .createSecondCategory(new ChildCategoryCreateRequest(parntCategoryName, "Top")));
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
        Category clothes = categoryService.createParentsCategory(new ParentsCategoryCreateRequest("Clothes"));

        Category bag = categoryService.createParentsCategory(new ParentsCategoryCreateRequest("Bag"));

        Category top = categoryService.createSecondCategory(new ChildCategoryCreateRequest(clothes.getId() + "", "Top"));

        categoryService.updateCategory(top.getId(),new ChildCategoryCreateRequest(bag.getId()+"","Backpack"));

        assertThat(top.getName()).isEqualTo("Backpack");
        assertThat(top.getParent().getId()).isEqualTo(bag.getId());
    }

    @Test
    void 일차카테고리_조회_성공(){
        category1 = categoryRepository.save(new Category("Clothes"));
        category2 =  categoryRepository.save(new Category("Shoes"));
        category3 = categoryRepository.save(new Category("Bag"));
        category4 =  categoryRepository.save(new Category("Accessories"));

        List<Category> categories = categoryRepository.foundParentCategory().orElseThrow();

        Assertions.assertThat(categories.get(0).getName()).isEqualTo("Clothes");
        Assertions.assertThat(categories.get(1).getName()).isEqualTo("Shoes");
        Assertions.assertThat(categories.get(2).getName()).isEqualTo("Bag");
        Assertions.assertThat(categories.get(3).getName()).isEqualTo("Accessories");
    }
}
