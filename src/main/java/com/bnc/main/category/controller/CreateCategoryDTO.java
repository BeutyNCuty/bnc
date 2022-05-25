package com.bnc.main.category.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class CreateCategoryDTO {
    @Getter
    @AllArgsConstructor
    @Builder
    public static class Info {
        private String parentCategory;
        private String childCategory;

        @Getter
        @Setter
        public static class Request {
            private String parentCategory;
            private String childCategory;
        }

        @Getter
        @AllArgsConstructor
        public static class Response {

            private String childCategoryName;
            private String parent_Id;
        }
    }
}