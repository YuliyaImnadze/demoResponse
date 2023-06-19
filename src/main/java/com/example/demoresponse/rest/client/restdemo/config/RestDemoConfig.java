package com.example.demoresponse.rest.client.restdemo.config;


import com.example.demoresponse.rest.client.restdemo.RestDemoRestComponent;
import com.example.demoresponse.rest.client.restdemo.v1.client.UserDTOClientV1;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestDemoConfig {

    @Bean(name = "UserDTOClientV1")
    public UserDTOClientV1 getUserDTOClient(RestTemplateBuilder restTemplateBuilder, RestDemoRestComponent restDemoRestComponent) {
        return new UserDTOClientV1(restTemplateBuilder, restDemoRestComponent);
    }

}
