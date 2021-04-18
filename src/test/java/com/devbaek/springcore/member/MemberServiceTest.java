package com.devbaek.springcore.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

    @Test
    @DisplayName("joinTest")
    void joinTest() {
        // given
        Member memberA = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(memberA);
        Member member = memberService.findMember(1L);

        // then
        assertThat(memberA.getName()).isEqualTo(member.getName());
    }
}
