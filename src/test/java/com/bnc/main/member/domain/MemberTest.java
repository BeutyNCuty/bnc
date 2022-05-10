package com.bnc.main.member.domain;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;


class MemberTest {

    @Test
    void 멤버_생성_성공(){
        final Member member = new Member("goyounha11", "1", "부평구","01051431509");

        assertThat(member.getUserId()).isEqualTo("goyounha11");
        assertThat(member.getPassword()).isEqualTo("1");
        assertThat(member.getAddr()).isEqualTo("부평구");
        assertThat(member.getPhone()).isEqualTo("01051431509");
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 아이디가_null_이거나_빈값_이면_실패(String usreId) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Member(usreId, "1", "부평구","01051431509"));
    }

    @Test
    void 아이디가_공백이면_실패() {
        String userId = "  ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Member(userId, "1", "부평구","01051431509"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 비밀번호가_null_이거나_빈값_이면_실패(String password){
        assertThatIllegalArgumentException().isThrownBy(() -> new Member("rkdwlstmf",password, "d안","01051431509"));
    }

    @Test
    void 비밀번호가_공백이면_실패(){
        String password = " ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Member("1", password , "부평구","010-123-123"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 주소가_null_이거나_빈값이면_실패(String addr){
        assertThatIllegalArgumentException().isThrownBy(() -> new Member("abcde", "1234", addr,"01078945612"));
    }
    
    @Test
    void 주소가_공백이면_실패(){
        String addr = " ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Member("fghi", "12345",addr,"01012345678"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void 폰번호가_null_이거나_빈값이면_실패(String phone){
        assertThatIllegalArgumentException().isThrownBy(() -> new Member("abcde", "1234", "addr", phone));
    }

    @Test
    void 폰번호가_공백이면_실패(){
        String phoneNumber = " ";

        assertThatIllegalArgumentException().isThrownBy(() -> new Member("abcde", "1234", "addr", phoneNumber));
    }
}
