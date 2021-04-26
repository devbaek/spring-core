package com.devbaek.springcore.scan;

import com.devbaek.springcore.AutoAppConfig;
import com.devbaek.springcore.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

class AutoConfigTest {

    @Test
    @DisplayName("basic 스캔 테스트")
    void basicScanTest() {
        // given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        // when
        MemberService memberService = ac.getBean(MemberService.class);
        // then
        assertThat(memberService).isInstanceOf(MemberService.class);
    }
}
