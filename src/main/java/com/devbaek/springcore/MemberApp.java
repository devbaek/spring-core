package com.devbaek.springcore;

import com.devbaek.springcore.member.Grade;
import com.devbaek.springcore.member.Member;
import com.devbaek.springcore.member.MemberService;
import com.devbaek.springcore.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member memberA = new Member(1L, "memberA", Grade.VIP);
        memberService.join(memberA);

        Member member = memberService.findMember(1L);
        System.out.println("memberA = " + memberA);
        System.out.println("member = " + member);
    }
}
