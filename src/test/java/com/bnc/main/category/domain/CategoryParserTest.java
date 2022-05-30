package com.bnc.main.category.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static com.bnc.main.category.controller.CreateCategoryDTO.ChildCategoryCreateRequest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class CategoryParserTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void createCategoryEntity() {
        Category parentCategory1 = new Category("Clothes");
        Category parentCategory2 = new Category("Bag");
        Category parentCategory3 = new Category("Shoes");
        Category parentCategory4 = new Category("Accessory");

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
    void 카테고리_디티오_에서_엔티티로_변환_성공() {
        ChildCategoryCreateRequest categoryDTO = new ChildCategoryCreateRequest("1", "Top");

        Category categoryEntity = CategoryParser.parserCategoryDtoToEntity(categoryDTO);

        assertThat(categoryEntity.getName()).isEqualTo(categoryDTO.getChildCategoryName());
    }

    @Test
    void 카테고리_엔티티_에서_디티오_변환_성공() {
        Category foundById = categoryRepository.findById(5L).orElseThrow();

        ChildCategoryCreateRequest request = CategoryParser.parserCategoryEntityToDto(foundById);

        assertThat(foundById.getName()).isEqualTo(request.getParentCategoryName());
    }
}
