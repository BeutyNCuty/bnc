package com.bnc.main.category.service;

import com.bnc.main.category.controller.CreateCategoryDTO;
import com.bnc.main.category.domain.Category;
import com.bnc.main.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createSecondCategory(CreateCategoryDTO.Info newCategory ) {

        Category firstCategory = categoryRepository.getById(Long.parseLong(newCategory.getParentCategory()));

        Category childCategory  = new Category(newCategory.getChildCategory());

        firstCategory.addChildCategory(childCategory);

        return firstCategory;
    }

    @Override
    public Optional<List<Category>> categoryClassification(Long parentId) {

        Optional<List<Category>> childByParentId = categoryRepository.getChildByParentId(parentId);

        return childByParentId;
    }

    @Override
    public void updateCategory(Long id , CreateCategoryDTO.Info category) {

       Category findById = categoryRepository.findById(id).orElseThrow();

       findById.updateCategory(category);
    }

    @Override
    public Optional<List<Category>> findFirstChildCategory() {

        Optional<List<Category>> firstChildCategory = categoryRepository.findFirstChildCategory();

        return firstChildCategory;
    }
}
