package com.bnc.main.category.controller;

import com.bnc.main.category.service.DefaultCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class CategoryRestController {

    private final DefaultCategoryService categoryService;

    @PostMapping("/createCategory")
    public CreateCategoryDTO.Info createSecondCategory(@RequestBody CreateCategoryDTO.Info secondCategoryName) {

        categoryService.createSecondCategory(secondCategoryName);

        return secondCategoryName;

    }
   /* @GetMapping("/categoryClassification")
    public void categoryClassification(@RequestBody String parentCategoryId) {

     //josn  형태로바꿔서
    }*/
}
