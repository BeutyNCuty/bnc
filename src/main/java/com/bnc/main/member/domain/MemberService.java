package com.bnc.main.member.domain;

import org.springframework.stereotype.Service;

@Service
public interface MemberService {

    Member mem = new Member();



    void join(Member member);

    Member findMember(Long memberId);
}
