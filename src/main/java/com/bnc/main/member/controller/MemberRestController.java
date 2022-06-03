package com.bnc.main.member.controller;

import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreatRequest;
import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateResponse;
import com.bnc.main.member.controller.dto.MemberDetailDto.MemberDetailResponse;
import com.bnc.main.member.controller.dto.MemberUpdateDto.MemberUpdateRequest;
import com.bnc.main.member.controller.dto.MemberUpdateDto.MemberUpdateResponse;
import com.bnc.main.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import static com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateData.create;
import static com.bnc.main.member.controller.dto.MemberDetailDto.MemberDetailData.read;
import static com.bnc.main.member.controller.dto.MemberUpdateDto.MemberUpdateData.update;

@RestController
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @PostMapping("/joinMember")
    public MemberCreateResponse createMember(@RequestBody MemberCreatRequest req){
        val member = memberService.create(req.toDto());

       return new MemberCreateResponse(create(member));
    }

    @GetMapping("/detailMember/{id}")
    public MemberDetailResponse detailMember(@PathVariable long id){
        val member = memberService.member(id);

        return new MemberDetailResponse(read(member));
    }

    @PatchMapping("/modifyMember")
    public MemberUpdateResponse modifyMember(@RequestBody MemberUpdateRequest req){
        val member = memberService.memberUpdate(req.toDto());

        return new MemberUpdateResponse(update(member));
    }

    @PatchMapping("/deleteMember/{id}")
    public void deleteMember(@PathVariable long id){
        memberService.memberWithdrawal(id);
    }
}
