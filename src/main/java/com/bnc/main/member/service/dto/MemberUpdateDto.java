package com.bnc.main.member.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberUpdateDto {
    private String userId;
    private String password;
    private String addr;
    private String phone;
}
