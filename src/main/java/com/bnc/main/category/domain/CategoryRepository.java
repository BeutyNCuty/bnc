package com.bnc.main.category.domain;

import com.bnc.main.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<List<Category>> getChildByParentId(Long id);

    @Query("select m from Member m where m.userId = :userId")
    public Member findByUserId(@Param("userId") String UserId);

    @Query("select c from Category c where c.parent is null")
    Optional<List<Category>> findParentCategory();

    @Query("select c from Category c where  c.parent is not null")
    Optional<List<Category>> findFirstChildCategory();

    @Query("select c from Category c where c.parent = :parentId")
    Optional<List<Category>> categoryClassification(Long parentId);
}
