package com.bnc.main.category.domain;

import com.bnc.main.category.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.bnc.main.category.controller.CreateCategoryDTO.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CategoryParserTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void 카테고리_디티오_에서_엔티티로_변환_성공() {
        ChildCategoryCreateRequest categoryDTO = new ChildCategoryCreateRequest("1", "Top");

        Category categoryEntity = CategoryParser.parserCategoryDtoToEntity(categoryDTO);

        assertThat(categoryEntity.getName()).isEqualTo(categoryDTO.getChildCategoryName());
    }

    @Test
    void 카테고리_엔티티_에서_디티오_변환_성공() {
        Category parentsCategory = categoryService.createParentsCategory(new ParentsCategoryCreateRequest("Clothes"));

        Category top = categoryService.createSecondCategory(new ChildCategoryCreateRequest(parentsCategory.getId() + "", "Top"));

        ChildCategoryFoundResponse childCategorySearchResponse = CategoryParser.parserCategoryEntityToDto(top);

        assertThat(childCategorySearchResponse.getChilCategoryName()).isEqualTo(top.getName());
        assertThat(childCategorySearchResponse.getParentCategoryId()).isEqualTo(top.getParent().getId()+"");
    }
}
