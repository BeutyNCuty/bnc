package com.bnc.main.member.domain;

import com.bnc.main.support.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.Entity;
import javax.persistence.Table;
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
    private OffsetDateTime creatAt = OffsetDateTime.now();

    public Member(String userId, String password, String addr, String phone) {
        checkArgument(Strings.isNotBlank(userId));
        checkArgument(Strings.isNotBlank(password));
        checkArgument(Strings.isNotBlank(addr));
        checkArgument(Strings.isNotBlank(phone));

        this.userId = userId;
        this.password = password;
        this.addr = addr;
        this.phone = phone;
    }
}
