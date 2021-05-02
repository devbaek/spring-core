package com.devbaek.springcore.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

class SingletonTest {

    @Test
    @DisplayName("싱글톤 빈 검색 테스트")
    void 싱글톤_빈_검색_테스트() throws Exception {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        // when
        SingletonBean singletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        // then
        Assertions.assertThat(singletonBean1).isSameAs(singletonBean2);
        ac.close();
    }

    @Scope("singleton")
    private static class SingletonBean {
        @PostConstruct
        public void init() {
            System.out.println("Singleton Bean Init!");
        }

        @PreDestroy
        public void close() {
            System.out.println("Singleton Bean Destroyed!!1");
        }
    }
}
