package com.bnc.main.category.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CreateCategoryDTO {

    @Getter
    @AllArgsConstructor
    public static class ParentsCategoryCreateRequest {
        private String parentCategoryName;
    }

    @Getter
    @AllArgsConstructor
    public static class ChildCategoryCreateRequest {
        private String parentCategoryName;
        private String childCategoryName;
    }
}
