package com.devbaek.springcore.web;

import com.devbaek.springcore.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final MyLogger myLogger;

    public void log(String id) {
        myLogger.log("service id = " + id);
    }
}
