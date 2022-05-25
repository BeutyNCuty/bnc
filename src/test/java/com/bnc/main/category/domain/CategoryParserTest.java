package com.bnc.main.category.domain;

import com.bnc.main.category.controller.CreateCategoryDTO;
import com.bnc.main.testSupport.BaseServiceTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CategoryParserTest extends BaseServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void 카테고리_디티오_에서_엔티티로_변환_성공() {
        CreateCategoryDTO.Info categoryDTO = new CreateCategoryDTO.Info("3", "name");

        Category categoryEntity = CategoryParser.parserCategoryDtoToEntity(categoryDTO);

        Assertions.assertThat(categoryEntity.getName()).isEqualTo(categoryDTO.getChildCategory());
    }

    @Test
    void 카테고리_엔티티_에서_디티오_변환_성공() {
        Category findById = categoryRepository.findById(6L).orElseThrow();

        CreateCategoryDTO.Info.Request request = CategoryParser.parserCategoryEntityToDto(findById);

        Assertions.assertThat(findById.getParent().getId()+"").isEqualTo(request.getParentCategory());
        Assertions.assertThat(findById.getName()).isEqualTo(request.getChildCategory());

    }
}
