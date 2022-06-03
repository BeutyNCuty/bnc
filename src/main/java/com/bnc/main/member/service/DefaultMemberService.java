package com.bnc.main.member.service;

import com.bnc.main.member.domain.Grade;
import com.bnc.main.member.domain.Member;
import com.bnc.main.member.domain.MemberRepository;
import com.bnc.main.member.service.dto.MemberCreateDto;
import com.bnc.main.member.service.dto.MemberUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class DefaultMemberService implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<Member> findByGrade(Grade grade) {
        return memberRepository.findByGrade(grade);
    }

    @Override
    public Member checkId(String userId) {
        return memberRepository.findByUserId(userId).orElseThrow();
    }

    @Override
    public Member create(MemberCreateDto dto) {
        val member = new Member(dto.getUserId(), dto.getPassword(), dto.getAddr(), dto.getPhone());

        return memberRepository.save(member);
    }

    @Override
    public Member findById(long id) {
        return memberRepository.findById(id).orElseThrow();
    }

    @Override
    public Member updateMember(MemberUpdateDto dto){
        Member member = memberRepository.findByUserId(dto.getUserId()).orElseThrow();

        member.change(dto.getPassword(), dto.getAddr(), dto.getPhone());

        return member;
    }

    @Override
    public Member delete(long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.delete();

        return member;
    }
}
