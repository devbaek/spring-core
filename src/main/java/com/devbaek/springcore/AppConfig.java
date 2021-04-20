package com.devbaek.springcore;

import com.devbaek.springcore.discount.DiscountPolicy;
import com.devbaek.springcore.discount.RateDiscountPolicy;
import com.devbaek.springcore.member.MemberRepository;
import com.devbaek.springcore.member.MemberService;
import com.devbaek.springcore.member.MemberServiceImpl;
import com.devbaek.springcore.member.MemoryMemberRepository;
import com.devbaek.springcore.order.OrderService;
import com.devbaek.springcore.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
