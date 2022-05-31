package com.bnc.main.member.service;

import com.bnc.main.member.domain.Grade;
import com.bnc.main.member.domain.Member;
import com.bnc.main.member.service.dto.MemberCreateDto;

import java.util.List;

public interface MemberService  {

    public List<Member> findByGrade(Grade grade);

    public Member checkId(String userId);

    public Member create(MemberCreateDto member);
}
