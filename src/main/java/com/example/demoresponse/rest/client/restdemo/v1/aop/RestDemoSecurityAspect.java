package com.example.demoresponse.rest.client.restdemo.v1.aop;

import com.example.demoresponse.rest.client.restdemo.RestDemoRestComponent;
import com.example.demoresponse.rest.client.restdemo.RestDemoTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;


@Aspect
@Component
@Slf4j
public class RestDemoSecurityAspect {

    private final RestDemoTokenProvider restDemoTokenProvider;
    private final RestDemoRestComponent restDemoRestComponent;

    @Autowired
    public RestDemoSecurityAspect(RestDemoTokenProvider restDemoTokenProvider,
                                  RestDemoRestComponent restDemoRestComponent) {
        this.restDemoTokenProvider = restDemoTokenProvider;
        this.restDemoRestComponent = restDemoRestComponent;
    }

    @Pointcut(value = "execution(public * com.example.demoresponse.rest.client.restdemo.v1.client.UserDTOClientV1.*(..))")
    public void callToken() {
    }

    @Around(value = "callToken()", argNames = "pjp")
    public Object trowingToken(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed(pjp.getArgs());
        } catch (HttpClientErrorException e) {
            ResponseEntity<String> token = restDemoTokenProvider.getToken();
            if (token.getStatusCode().is2xxSuccessful()) {
                restDemoRestComponent.setRestToken(token.getBody());
                return pjp.proceed(pjp.getArgs());
            } else {
                log.error("Не валидный логин и пароль");
                System.exit(-1);
            }
        }
        return null;
    }
}



