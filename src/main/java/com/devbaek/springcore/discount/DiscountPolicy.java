package com.devbaek.springcore.discount;

import com.devbaek.springcore.member.Member;

public interface DiscountPolicy {

    // 할인 가격
    int discount(Member member, int price);
}
