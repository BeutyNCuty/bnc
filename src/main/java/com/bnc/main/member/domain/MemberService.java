package com.bnc.main.member.domain;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface MemberService  {



    public List<Member> checkId(String userId);  /*해당아이디유저조회*/

    public List<Member> findByGrade(Grade grade);  /*해당등급 유저조회*/

}