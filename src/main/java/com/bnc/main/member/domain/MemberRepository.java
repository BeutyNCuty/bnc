package com.bnc.main.member.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository  extends JpaRepository<Member, Long> {
    Member save(Member member);

    <Optional>Member findById(Long memberId);
}
