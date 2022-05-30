package com.bnc.main.member.controller;

import com.bnc.main.member.domain.Member;
import com.bnc.main.member.domain.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreatRequest;
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
        MemberCreatRequest req = new MemberCreatRequest("admin", "1", "지구", "01012345678");

            mockMvc.perform(post("/joinMember")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(req)))
                    .andDo(print())
                    .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.member.userId").value("admin"),
                        jsonPath("$.member.password").value("1"),
                        jsonPath("$.member.addr").value("지구"),
                        jsonPath("$.member.phone").value("01012345678")
                );
    }

    @Test
    void 멤버_조회_성공() throws Exception {
        Member req = memberRepository.save(new Member("admin", "1", "지구", "01012345678"));

        mockMvc.perform(get("/detailMember/" + req.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 멤버_수정_성공()throws Exception{
        Member req = memberRepository.save(new Member("admin", "1", "지구", "01012345678"));

        req.change("123","화성", "01054646464");

        mockMvc.perform(patch("/modifyMember")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(req)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpectAll(
                        jsonPath("$.member.password").value("123"),
                        jsonPath("$.member.addr").value("화성"),
                        jsonPath("$.member.phone").value("01054646464")
        );
    }
}
