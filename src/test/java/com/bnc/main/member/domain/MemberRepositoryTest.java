package com.bnc.main.member.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bnc.main.member.domain.Grade.*;
import static com.bnc.main.member.domain.MemberStatus.CREATED;
import static com.bnc.main.member.domain.MemberStatus.DELETED;
import static org.assertj.core.api.Assertions.assertThat;

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
        member1 = memberRepository.save(new Member("cc121", "123", "010", "부천"));
        member2 = memberRepository.save(new Member("cc123", "123", "010", "부천"));
        member3 = memberRepository.save(new Member("cc124", "123", "010", "부천"));
        member4 = memberRepository.save(new Member("cc125", "123", "010", "부천"));
        member5 = memberRepository.save(new Member("cc126", "123", "010", "부천"));
        member6 = memberRepository.save(new Member("cc127", "123", "010", "부천"));

        member1.grade = Gold;
        member2.grade = Gold;
        member3.grade = Silver;
        member4.grade = Silver;
    }

    @Test
    void 멤버_생성_성공() {
        Member member = new Member("cc1212", "123", "010", "부천");

        memberRepository.save(member);

        Member foundMember = memberRepository.findByUserId(member.getUserId()).orElseThrow();

        assertThat(foundMember.getId()).isNotNull();
        assertThat(foundMember.getUserId()).isEqualTo("cc1212");
        assertThat(foundMember.getMemberStatus()).isEqualTo(CREATED);
        assertThat(foundMember.getPassword()).isEqualTo("123");
        assertThat(foundMember.getAddr()).isEqualTo("010");
        assertThat(foundMember.getPhone()).isEqualTo("부천");
        assertThat(foundMember.getGrade()).isEqualTo(Bronze);
        assertThat(foundMember.getTotalPrice()).isEqualTo(0);
    }

    @Test
    void 유저_아이디_조회() {
        Member findByUserId = memberRepository.findByUserId("cc121").orElseThrow();

        assertThat(findByUserId).isEqualTo(member1);
    }

    @Test
    void 유저_아이디_조회_실패() {
        String loginId = "cc166";

        assertThat(memberRepository.findByUserId(loginId).isPresent()).isFalse();
    }

    @Test
    void 브론즈등급_유저_조회() {
        List<Member> foundGradeMember = memberRepository.findByGrade(Bronze);

        assertThat(foundGradeMember).containsExactly(member5, member6);
    }

    @Test
    void 실버등급_유저_조회() {
        List<Member> foundGradeMember = memberRepository.findByGrade(Silver);

        assertThat(foundGradeMember).containsExactly(member3, member4);
    }

    @Test
    void 골드등급_유저_조회() {
        List<Member> foundGradeMember = memberRepository.findByGrade(Gold);

        assertThat(foundGradeMember).containsExactly(member1, member2);
    }

    @Test
    void 아이디_중복체크() {
        int count = memberRepository.countByuserId("cc121");

        assertThat(count).isEqualTo(1);
    }

    @Test
    void 유저_정보_수정_성공() {
        Member foundMember = memberRepository.findByUserId(member1.getUserId()).orElseThrow();

        foundMember.change("123123", "서울", "01012341234");

        Member changedMember = memberRepository.findByUserId(member1.getUserId()).orElseThrow();

        assertThat(changedMember.getPassword()).isEqualTo("123123");
        assertThat(changedMember.getAddr()).isEqualTo("서울");
        assertThat(changedMember.getPhone()).isEqualTo("01012341234");
    }

    @Test
    void 유저_삭제_성공(){
        Member foundMember = memberRepository.findByUserId(member1.getUserId()).orElseThrow();

        foundMember.delete();

        Member deletedMember = memberRepository.findByUserId(member1.getUserId()).orElseThrow();

        assertThat(deletedMember.getMemberStatus()).isEqualTo(DELETED);
    }
}
