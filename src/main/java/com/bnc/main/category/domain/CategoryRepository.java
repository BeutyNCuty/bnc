package com.bnc.main.category.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<List<Category>> getChildByParentId(Long id);

    @Query("select c from Category c where c.parent.id is null")
    Optional<List<Category>> foundParentCategory();

    @Query("select c from Category c where  c.parent.id is not null")
    Optional<List<Category>> foundAllChildCategory();

    @Query("select c from Category c where c.parent = :parentId")
    Optional<List<Category>> categoryClassification(Long parentId);
}
