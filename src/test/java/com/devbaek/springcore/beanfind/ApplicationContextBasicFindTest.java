package com.devbaek.springcore.beanfind;

import com.devbaek.springcore.AppConfig;
import com.devbaek.springcore.member.MemberService;
import com.devbaek.springcore.member.MemberServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findByBeanName() {
        // when
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 타입으로 조회")
    void findByBeanType() {
        // when
        MemberService memberService = ac.getBean(MemberService.class);
        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findByBeanName2() {
        // when
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        // then
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회 X")
    void findBeanByNameX(){
        // when
        Throwable throwable = catchThrowable(() -> ac.getBean("xxx", MemberService.class));
        // then
        assertThat(throwable)
                .isInstanceOf(NoSuchBeanDefinitionException.class);
    }
}
