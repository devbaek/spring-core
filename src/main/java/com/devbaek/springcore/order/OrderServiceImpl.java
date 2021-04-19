package com.devbaek.springcore.order;

import com.devbaek.springcore.discount.DiscountPolicy;
import com.devbaek.springcore.discount.FixDiscountPolicy;
import com.devbaek.springcore.member.Member;
import com.devbaek.springcore.member.MemberRepository;
import com.devbaek.springcore.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
