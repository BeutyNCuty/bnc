package com.bnc.main.member.domain;

import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateData;
import com.bnc.main.member.service.DefaultMemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.bnc.main.member.domain.Grade.Bronze;
import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
@WebAppConfiguration
class DefaultMemberServiceTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    DefaultMemberService defaultMemberService;

    private Member member1;
    private Member member2;
    private Member member3;

    @BeforeEach
    void setUp() {
        member1 = memberRepository.save(new Member( "cc121", "123", "010", "부천"));
        member2 = memberRepository.save(new Member( "cc122", "123", "010", "부천"));
        member3 = memberRepository.save(new Member( "cc123", "123", "010", "부천"));
    }

    @Test
    void 등급별_유저_조회_성공(){
        List<Member> byGrade = defaultMemberService.findByGrade(Bronze);  // 브론즈에해당하는 유저 전부 셀렉

        for (Member member : byGrade) {
            assertThat(member.getGrade()).isEqualByComparingTo(Bronze); // 이터레이터돌면서 전부 브론즈인지확인하고맞으면 성공
        }
    }
    @Test
    void 로그인_성공(){
        Member savedMember = memberRepository.login(member1.getUserId(), member1.getPassword()).orElseThrow();

        MemberCreateData foundMember = MemberCreateData.create(savedMember);
        System.out.println("12234" + member1.getUserId());
        System.out.println(member1.getPassword());
        assertThat(foundMember.getUserId()).isEqualTo("cc121");
        assertThat(foundMember.getPassword()).isEqualTo("123");
    }
}