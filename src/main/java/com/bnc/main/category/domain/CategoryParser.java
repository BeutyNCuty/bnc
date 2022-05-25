package com.bnc.main.category.domain;
import com.bnc.main.category.controller.CreateCategoryDTO;

public class CategoryParser{

    public static Category parserCategoryDtoToEntity(CreateCategoryDTO.Info categoryDTO){

        return new Category(
                categoryDTO.getChildCategory()
        );
    }

    public static CreateCategoryDTO.Info.Request parserCategoryEntityToDto(Category categoryEntity){

        CreateCategoryDTO.Info.Request categoryDTO = new CreateCategoryDTO.Info.Request();

        categoryDTO.setParentCategory(categoryEntity.getParent().getId()+"");
        categoryDTO.setChildCategory(categoryEntity.getName());

        return categoryDTO;
    }
}
