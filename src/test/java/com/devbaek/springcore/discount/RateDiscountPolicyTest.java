package com.devbaek.springcore.discount;

import com.devbaek.springcore.member.Grade;
import com.devbaek.springcore.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인 적용")
    void vipDiscountTest() {
        // given
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        // when
        int discount = discountPolicy.discount(memberA, 10000);

        // then
        assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("일반 회원은 10% 할인 적용 X")
    void basicDiscountTest() {
        // given
        Member memberA = new Member(1L, "memberA", Grade.BASIC);
        // when
        int discount = discountPolicy.discount(memberA, 10000);

        // then
        assertThat(discount).isZero();
    }

}