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
    public Category createParentsCategory(CreateCategoryDTO.ParentsCategoryCreateRequest newCategory){
        Category parentsCategory = new Category(newCategory.getParentCategoryName());

        Category saveParentsCategory = categoryRepository.save(parentsCategory);

        return saveParentsCategory;
    }

    @Override
    public Category createSecondCategory(CreateCategoryDTO.ChildCategoryCreateRequest newCategory ) {
        Category SaveChildCategory  = new Category(newCategory.getChildCategoryName());

        Category firstCategory = categoryRepository.getById(Long.parseLong(newCategory.getParentCategoryName()));

        firstCategory.addChildCategory(SaveChildCategory);

        return firstCategory;
    }

    @Override
    public Optional<List<Category>> categoryClassification(Long parentId) {
        Optional<List<Category>> childByParentId = categoryRepository.getChildByParentId(parentId);

        return childByParentId;
    }

    @Override
    public void updateCategory(Long id , CreateCategoryDTO.ChildCategoryCreateRequest category) {
       Category foundById = categoryRepository.findById(id).orElseThrow();

       foundById.updateCategory(category);
    }
}
