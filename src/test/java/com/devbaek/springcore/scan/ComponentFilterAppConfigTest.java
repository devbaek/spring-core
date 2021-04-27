package com.devbaek.springcore.scan;

import com.devbaek.springcore.scan.filter.MyExcludeComponent;
import com.devbaek.springcore.scan.filter.MyIncludeComponent;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.springframework.context.annotation.ComponentScan.Filter;

class ComponentFilterAppConfigTest {

    @Test
    @DisplayName("filter scan test")
    void filterScanTest() {
        // given
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        // when
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Throwable beanB = catchThrowable(() -> ac.getBean("beanB", BeanB.class));
        // then
        assertThat(beanA).isNotNull();
        assertThat(beanB).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig {
    }

}
