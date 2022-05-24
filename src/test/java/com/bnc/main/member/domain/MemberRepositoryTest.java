package com.bnc.main.member.domain;

import com.bnc.main.member.controller.dto.MemberCreateDto;
import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateData;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bnc.main.member.domain.Grade.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    private Member member1;
    private Member member2;
    private Member member3;
    private Member member4;
    private Member member5;
    private Member member6;

    @BeforeEach
    void setUp() {
        member1 = memberRepository.save(new Member( "cc121", "123", "010", "부천"));
        member2 = memberRepository.save(new Member( "cc123", "123", "010", "부천"));
        member3 = memberRepository.save(new Member( "cc124", "123", "010", "부천"));
        member4 = memberRepository.save(new Member( "cc125", "123", "010", "부천"));
        member5 = memberRepository.save(new Member( "cc126", "123", "010", "부천"));
        member6 = memberRepository.save(new Member( "cc127", "123", "010", "부천"));

        member1.grade = Gold;
        member2.grade = Gold;
        member3.grade = Silver;
        member4.grade = Silver;
    }

    @Test
    void 유저_아이디_조회() {
        Member member = new Member( "cc122", "123", "010", "부천");
        memberRepository.save(member);

        String loginId = "cc122";

        Member findByUserId = memberRepository.findByUserId(loginId);

        assertThat(findByUserId).isEqualTo(member);
    }

    @Test
    void 브론즈등급_유저_조회() {
        List<Member> foundGradeMember = memberRepository.findByGrade(Bronze);

        assertThat(foundGradeMember).containsExactly(member5, member6);
    }

    @Test
    void 실버등급_유저_조회(){
        List<Member> foundGradeMember = memberRepository.findByGrade(Silver);

        assertThat(foundGradeMember).containsExactly(member3,member4);
    }

    @Test
    void 골드등급_유저_조회(){
        List<Member> foundGradeMember = memberRepository.findByGrade(Gold);

        assertThat(foundGradeMember).containsExactly(member1,member2);
    }

    @Test
    void 로그인_성공(){
        Member foundMember = memberRepository.login(member1.getUserId(), member1.getPassword());

        assertThat(foundMember.getUserId()).isEqualTo("cc121");
        assertThat(foundMember.getPassword()).isEqualTo("123");
    }
}
