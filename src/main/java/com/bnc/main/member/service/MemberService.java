package com.bnc.main.member.service;

import com.bnc.main.member.domain.Member;

public interface MemberService  {

    Member create(Member member);

    void delete(Member member);

}
