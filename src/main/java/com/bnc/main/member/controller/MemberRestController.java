package com.bnc.main.member.controller;

import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreatRequest;
import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateData;
import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateResponse;
import com.bnc.main.member.controller.dto.MemberUpdateDto;
import com.bnc.main.member.controller.dto.MemberUpdateDto.MemberUpdateRequest;
import com.bnc.main.member.controller.dto.MemberUpdateDto.MemberUpdateResponse;
import com.bnc.main.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.*;

import static com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateData.create;
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

    @GetMapping("/detailMember/{Id}")
    public MemberCreateResponse detailMember(@PathVariable long Id){

        val member = memberService.findById(Id);

        return new MemberCreateResponse(create(member));
    }

    @PatchMapping("/modifyMember")
    public MemberUpdateResponse modifyMember(@RequestBody MemberUpdateRequest req){
        val member = memberService.updateMember(req.toDto());

        return new MemberUpdateResponse(update(member));
    }
}
