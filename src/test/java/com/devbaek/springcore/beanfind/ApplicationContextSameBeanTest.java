package com.devbaek.springcore.beanfind;

import com.devbaek.springcore.member.MemberRepository;
import com.devbaek.springcore.member.MemoryMemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

class ApplicationContextSameBeanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanTestConfig.class);
    
    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상이면, 중복 오류 발생")
    void findBeanByTypeDuplicateTest() {
        // when
        Throwable throwable = catchThrowable(() -> ac.getBean(MemberRepository.class));
        // then
        assertThat(throwable).isInstanceOf(NoUniqueBeanDefinitionException.class);
    }
    
    @Test
    @DisplayName("타입으로 조회 시 같은 타입이 둘 이상이면, 빈 이름 지정")
    void findBeanByName() {
        // when
        MemberRepository memberRepository1 = ac.getBean("memberRepository1", MemberRepository.class);
        // then
        assertThat(memberRepository1).isInstanceOf(MemoryMemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 전체 조회")
    void findAllBeansByType() {
        // given
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        // when
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + " value" + beansOfType.get(key));
        }
        // then
        System.out.println("beansOfType = " + beansOfType);
        assertThat(beansOfType).hasSize(2);
    }

    @Configuration
    static class SameBeanTestConfig {
        @Bean
        public MemberRepository memberRepository1() {
            return new MemoryMemberRepository();
        }
        @Bean
        public MemberRepository memberRepository2() {
            return new MemoryMemberRepository();
        }
    }

}
