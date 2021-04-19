package com.devbaek.springcore;

import com.devbaek.springcore.discount.RateDiscountPolicy;
import com.devbaek.springcore.member.MemberService;
import com.devbaek.springcore.member.MemberServiceImpl;
import com.devbaek.springcore.member.MemoryMemberRepository;
import com.devbaek.springcore.order.OrderService;
import com.devbaek.springcore.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new RateDiscountPolicy());
    }
}
