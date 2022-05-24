package com.bnc.main.member.controller;

import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreatRequest;
import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateData;
import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateResponse;
import com.bnc.main.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/joinMember")
    public MemberCreateResponse createMember(@RequestBody MemberCreatRequest req){

        val member = memberService.create(req.toDto());
       return new MemberCreateResponse(MemberCreateData.create(member));

    }
}
