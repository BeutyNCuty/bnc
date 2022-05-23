package com.bnc.main.member.controller;

import com.bnc.main.member.controller.dto.MemberCreateDto;
import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreatRequest;
import com.bnc.main.member.controller.dto.MemberCreateDto.MemberCreateData;
import com.bnc.main.member.service.DefaultMemberService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MemberRestController {

    @Autowired
    private DefaultMemberService defaultMemberService;

    @PostMapping(value = "/joinUser")
    public ModelAndView createMember(MemberCreatRequest req, ModelAndView mv) throws Exception {
        val member = defaultMemberService.create(req.toDto());

        mv.setViewName("views/member/login");

        return mv;
    }

    @PostMapping(value = "/memberLogin")
    public ModelAndView memberLogin(ModelAndView mv, MemberCreatRequest memberReq, HttpServletRequest req)throws Exception{

        MemberCreateData member = defaultMemberService.loginSuccess(memberReq.toDto());

        if (member != null){
            req.getSession().setAttribute("user", member);
            mv.setViewName("index");
        }else {
            mv.setViewName("views/member/login");
        }
        return mv;
    }
}
