package com.bnc.main.member.domain;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static com.bnc.main.member.domain.memberStatus.DELETED;
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

    @Test
    void 정보_변경_성공() {
       final Member member = new Member( "1", "addr", "01052587376");

       member.change("2", "aaa", "ffff");

       assertThat(member.getPassword()).isEqualTo("2");
       assertThat(member.getAddr()).isEqualTo("aaa");
       assertThat(member.getPhone()).isEqualTo("ffff");
    }

    @Test
    void 정보_삭제_성공() {
        final Member member = new Member("1", "addr", "01052587376");

        member.delete();

        assertThat(member.getMemberStatus()).isEqualTo(DELETED);
    }

    @Test
    void 비밀번호_변경_성공(){
        final Member member = new Member( "1", "addr", "01052587376");

        member.changePassword("2");

        assertThat(member.getPassword()).isEqualTo("2");
    }

    @Test
    void 비밀번호_변경_실패(){
        final Member member = new Member( "1", "addr", "01052587376");

        member.changePassword("2");

        assertThat(member.getPassword()).isNotEqualTo("2");
    }

    @Test
    void 등급_Gold(){
       final Member member = new Member();
       member.checkGrade(510000);

       assertThat(member.getGrade()).isEqualTo("Gold");
    }

    @Test
    void 등급_Silver(){
        final Member member = new Member();
        member.checkGrade(210000);

        assertThat(member.getGrade()).isEqualTo("Silver");
    }

    @Test
    void 등급_Bronze(){
        final Member member = new Member();
        member.checkGrade(100000);

        assertThat(member.getGrade()).isEqualTo("Bronze");
    }
}
