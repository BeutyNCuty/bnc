package com.bnc.main.category.controller;

import com.bnc.main.category.service.DefaultCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.bnc.main.category.controller.CreateCategoryDTO.ChildCategoryCreateRequest;

@RestController
@RequiredArgsConstructor
public class CategoryRestController {

    private final DefaultCategoryService categoryService;

    @PostMapping("/createSecondCategory")
    public ChildCategoryCreateRequest createSecondCategory(@RequestBody ChildCategoryCreateRequest secondCategoryName) {
        categoryService.createSecondCategory(secondCategoryName);

        return secondCategoryName;
    }
}
