package com.bnc.main.category.domain;

import static com.bnc.main.category.controller.CreateCategoryDTO.ChildCategoryCreateRequest;
import static com.bnc.main.category.controller.CreateCategoryDTO.ChildCategoryFoundResponse;

public class CategoryParser {

    public static Category parserCategoryDtoToEntity(ChildCategoryCreateRequest categoryDTO) {
        return new Category(
                categoryDTO.getChildCategoryName()
        );
    }

    public static ChildCategoryFoundResponse parserCategoryEntityToDto(Category categoryEntity) {
        return new ChildCategoryFoundResponse(categoryEntity.getParent().getId()+""
                , categoryEntity.getName());
    }
}
