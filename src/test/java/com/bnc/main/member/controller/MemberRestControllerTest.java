package com.bnc.main.member.controller;

import com.bnc.main.member.domain.Member;
import com.bnc.main.member.domain.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreatRequest;
import static com.bnc.main.member.domain.Grade.Bronze;
import static com.bnc.main.member.domain.MemberStatus.CREATED;
import static com.bnc.main.member.domain.MemberStatus.DELETED;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = RANDOM_PORT)
class MemberRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void 멤버_생성_성공() throws Exception {
        MemberCreatRequest member = new MemberCreatRequest("admin", "1", "지구", "01012345678");

        mockMvc.perform(post("/joinMember")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(member)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.member.userId").value("admin"),
                        jsonPath("$.member.password").value("1"),
                        jsonPath("$.member.addr").value("지구"),
                        jsonPath("$.member.phone").value("01012345678"),
                        jsonPath("$.member.grade").value(Bronze.name()),
                        jsonPath("$.member.memberStatus").value(CREATED.name()),
                        jsonPath("$.member.totalPrice").value(0),
                        jsonPath("$.member.creatAt").isNotEmpty()
        );
    }

    @Test
    void 멤버_조회_성공() throws Exception {
        Member member = memberRepository.save(new Member("admin", "1", "지구", "01012345678"));

        mockMvc.perform(get("/detailMember/{id}", member.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpectAll(
                        jsonPath("$.member.userId").value("admin"),
                        jsonPath("$.member.password").value("1"),
                        jsonPath("$.member.addr").value("지구"),
                        jsonPath("$.member.phone").value("01012345678"),
                        jsonPath("$.member.grade").value(Bronze.name()),
                        jsonPath("$.member.memberStatus").value(CREATED.name()),
                        jsonPath("$.member.totalPrice").value(0),
                        jsonPath("$.member.creatAt").isNotEmpty()
                );
    }

    @Test
    void 멤버_수정_성공() throws Exception{
        Member member = memberRepository.save(new Member("admin", "1", "지구", "01012345678"));

        member.change("123","화성", "01054646464");

        mockMvc.perform(patch("/modifyMember")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(member)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.member.userId").value("admin"),
                        jsonPath("$.member.password").value("123"),
                        jsonPath("$.member.addr").value("화성"),
                        jsonPath("$.member.phone").value("01054646464"),
                        jsonPath("$.member.grade").value(Bronze.name()),
                        jsonPath("$.member.memberStatus").value(CREATED.name()),
                        jsonPath("$.member.totalPrice").value(0),
                        jsonPath("$.member.creatAt").isNotEmpty()
        );
    }

    @Test
    void 멤버_삭제_성공() throws Exception{
        Member member = memberRepository.save(new Member("admin", "1", "지구", "01012345678"));

        mockMvc.perform(patch("/deleteMember/{id}", member.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(member)))
                .andDo(print())
                .andExpect(status().isOk());

        Assertions.assertThat(member.getMemberStatus()).isEqualTo(DELETED);
    }
}
