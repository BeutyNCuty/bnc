package com.bnc.main.member.domain;

public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);


}
