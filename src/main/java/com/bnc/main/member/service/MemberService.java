package com.bnc.main.member.service;

import com.bnc.main.member.domain.Grade;
import com.bnc.main.member.domain.Member;
import com.bnc.main.member.service.dto.MemberCreateDto;
import com.bnc.main.member.service.dto.MemberUpdateDto;

import java.util.List;
import java.util.Optional;

public interface MemberService  {

    public List<Member> findByGrade(Grade grade);

    public Member checkId(String userId);

    public Member create(MemberCreateDto member);

    public Member findById(long id);

    public Member updateMember(MemberUpdateDto member);

    public Member delete(long id);
}
