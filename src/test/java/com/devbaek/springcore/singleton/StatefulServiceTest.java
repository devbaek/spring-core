package com.devbaek.springcore.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    @DisplayName("Stateful 싱글톤 테스트")
    void statefulServiceSingletonTest() {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);
        // when

        // ThreadA: A 사용자 10000원 주문 주문
        statefulService1.order("userA", 10000);
        // ThreadB: B 사용자 20000원 주문
        statefulService2.order("userB", 20000);

        //ThreadA: A 사용자 주문 금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        // then
        assertThat(price).isEqualTo(20000);
    }

    @Test
    @DisplayName("Stateless 싱글톤 테스트")
    void statelessServiceSingletonTest() {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(StatelessTestConfig.class);
        StatefulService.StatelessService statefulService1 = ac.getBean(StatefulService.StatelessService.class);
        StatefulService.StatelessService statefulService2 = ac.getBean(StatefulService.StatelessService.class);
        // when

        // ThreadA: A 사용자 10000원 주문 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B 사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);


        //ThreadA: A 사용자 주문 금액 조회
        int price = userAPrice;
        System.out.println("price = " + price);

        // then
        assertThat(price).isEqualTo(10000);
    }


    static class TestConfig {
        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

    static class StatelessTestConfig {
        @Bean
        public StatefulService.StatelessService statefulService() {
            return new StatefulService.StatelessService();
        }
    }
}
