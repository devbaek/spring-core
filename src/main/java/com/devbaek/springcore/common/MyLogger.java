package com.devbaek.springcore.common;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
@Log4j2
public class MyLogger {

    private String uuid;
    private String requestUrl;

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public void log(String message) {
        log.info("[{}] [{}]{}", uuid, requestUrl, message);
    }

    @PostConstruct
    public void init() {
        this.uuid = UUID.randomUUID().toString();
        log.info("[{}] request bean created:{}", uuid, this);
    }

    @PreDestroy
    public void close() {
        log.info("[{}] request bean closed:{}", uuid, this);
    }
}
