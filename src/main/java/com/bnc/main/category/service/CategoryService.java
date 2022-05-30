package com.bnc.main.category.service;

import com.bnc.main.category.controller.CreateCategoryDTO;
import com.bnc.main.category.domain.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createParentsCategory(CreateCategoryDTO.ParentsCategoryCreateRequest newCategory);

    Category createSecondCategory(CreateCategoryDTO.ChildCategoryCreateRequest newCategory);

    Optional<List<Category>> categoryClassification(Long parentId);

    void updateCategory(Long id, CreateCategoryDTO.ChildCategoryCreateRequest category);
}
