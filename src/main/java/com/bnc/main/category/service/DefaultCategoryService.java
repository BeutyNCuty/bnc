package com.bnc.main.category.service;

import com.bnc.main.category.domain.Category;
import com.bnc.main.category.domain.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bnc.main.category.controller.CreateCategoryDTO.ChildCategoryCreateRequest;
import static com.bnc.main.category.controller.CreateCategoryDTO.ParentsCategoryCreateRequest;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultCategoryService implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createParentsCategory(ParentsCategoryCreateRequest newParentsCategory){
        Category parentsCategory = new Category(newParentsCategory.getParentCategoryName());

        Category saveParentsCategory = categoryRepository.save(parentsCategory);

        return saveParentsCategory;
    }

    @Override
    public Category createSecondCategory(ChildCategoryCreateRequest newChildCategory ) {
        Category childCategory  = new Category(newChildCategory.getChildCategoryName());

        Category saveChildCategory = categoryRepository.save(childCategory);

        Category firstCategory = categoryRepository.getById(Long.parseLong(newChildCategory.getParentCategoryName()));

        saveChildCategory.setParent(firstCategory);

        return saveChildCategory;
    }

    @Override
    public Optional<List<Category>> categoryClassification(Long parentId) {
        Optional<List<Category>> childByParentId = categoryRepository.getChildByParentId(parentId);

        return childByParentId;
    }

    @Override
    public void updateCategory(Long id , ChildCategoryCreateRequest category) {
       Category foundById = categoryRepository.findById(id).orElseThrow();

       foundById.updateCategory(category);
    }
}
