package com.bnc.main.category.domain;

import static com.bnc.main.category.controller.CreateCategoryDTO.ChildCategoryCreateRequest;

public class CategoryParser {

    public static Category parserCategoryDtoToEntity(ChildCategoryCreateRequest categoryDTO) {
        return new Category(
                categoryDTO.getChildCategoryName()
        );
    }

    public static ChildCategoryCreateRequest parserCategoryEntityToDto(Category categoryEntity) {
        return new ChildCategoryCreateRequest(categoryEntity.getName()
                , categoryEntity.getParent().getId() + "");
    }
}
