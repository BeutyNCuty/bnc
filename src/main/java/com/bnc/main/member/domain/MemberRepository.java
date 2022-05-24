package com.bnc.main.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m "+
            "from Member m "+
            "where m.userId = :userId"
    )
    Optional<Member> findByUserId(String userId);

    @Query("select m "+
            "from Member m "+
            "where m.userId = :userId and m.password = :password"
    )
    Optional<Member> login(String userId, String password);

    @Query("select m "+
            "from Member m "+
            "where m.grade = :grade"
    )
    List<Member> findByGrade(Grade grade);
}
