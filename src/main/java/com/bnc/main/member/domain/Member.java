package com.bnc.main.member.domain;

import com.bnc.main.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;
import java.time.OffsetDateTime;

import static com.google.common.base.Preconditions.checkArgument;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    private String userId;
    private String password;
    private String addr;
    private String phone;
    private String grade;
    private long totalPrice;
    private OffsetDateTime creatAt = OffsetDateTime.now();
    private memberStatus memberStatus;





    public Member(String userId, String password, String addr, String phone , long totalPrice) {
        checkArgument(Strings.isNotBlank(userId));
        checkArgument(Strings.isNotBlank(password));
        checkArgument(Strings.isNotBlank(addr));
        checkArgument(Strings.isNotBlank(phone));

        this.userId = userId;
        this.password = password;
        this.addr = addr;
        this.phone = phone;
        this.totalPrice = totalPrice;
    }

    public void change(String password, String addr, String phone){
        this.password = password;
        this.addr = addr;
        this.phone = phone;
    }

    public Member(String password, String addr, String phone) {
        this.password = password;
        this.addr = addr;
        this.phone = phone;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void delete(){
        this.memberStatus = memberStatus.DELETED;
    }

    public void checkGrade(long price){

        if(price >= 0 && price < 200000){
            this.grade = "Bronze";
        }
        else if(price >= 200000 && price <= 500000){
            this.grade = "Silver";
        }
        else {
            this.grade = "Gold";
        }
    }
}
