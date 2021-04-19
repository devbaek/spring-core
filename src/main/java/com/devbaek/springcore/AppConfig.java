package com.devbaek.springcore;

import com.devbaek.springcore.discount.DiscountPolicy;
import com.devbaek.springcore.discount.RateDiscountPolicy;
import com.devbaek.springcore.member.MemberRepository;
import com.devbaek.springcore.member.MemberService;
import com.devbaek.springcore.member.MemberServiceImpl;
import com.devbaek.springcore.member.MemoryMemberRepository;
import com.devbaek.springcore.order.OrderService;
import com.devbaek.springcore.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(getMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(getMemberRepository(), getDiscountPolicy());
    }

    private DiscountPolicy getDiscountPolicy() {
        return new RateDiscountPolicy();
    }

    private MemberRepository getMemberRepository() {
        return new MemoryMemberRepository();
    }
}
