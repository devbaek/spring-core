package com.devbaek.springcore.singleton;

import com.devbaek.springcore.AppConfig;
import com.devbaek.springcore.member.MemberRepository;
import com.devbaek.springcore.member.MemberServiceImpl;
import com.devbaek.springcore.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class ConfigurationSingletonTest {

    @Test
    @DisplayName("Configuration 싱글톤 테스트")
    void configurationTest() {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // when
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        // then
        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Test
    @DisplayName("Configuration 테스트")
    void configurationDeepTest() {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        // when
        AppConfig bean = ac.getBean(AppConfig.class);

        // then
        System.out.println("bean = " + bean);
        assertThat(bean).isInstanceOf(AppConfig.class).isNotSameAs(AppConfig.class);
    }
}
