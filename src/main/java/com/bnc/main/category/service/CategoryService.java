package com.bnc.main.category.service;

import com.bnc.main.category.domain.Category;

import java.util.List;
import java.util.Optional;

import static com.bnc.main.category.controller.CreateCategoryDTO.ChildCategoryCreateRequest;
import static com.bnc.main.category.controller.CreateCategoryDTO.ParentsCategoryCreateRequest;

public interface CategoryService {
    Category createParentsCategory(ParentsCategoryCreateRequest newParantsCategory);

    Category createSecondCategory(ChildCategoryCreateRequest newChildCategory);

    Optional<List<Category>> categoryClassification(Long parentId);

    void updateCategory(Long id, ChildCategoryCreateRequest category);
}
