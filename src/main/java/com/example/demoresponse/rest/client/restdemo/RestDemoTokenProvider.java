package com.example.demoresponse.rest.client.restdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestDemoTokenProvider {


    private final RestTemplate restTemplate;

    @Value(value = "${rest.client.restdemo.login}")
    private String login;

    @Value(value = "${rest.client.restdemo.password}")
    private String password;

    @Value(value = "${rest.client.restdemo.base.url}${rest.client.restdemo.token.urn}")
    private String tokenUri;


    @Autowired
    public RestDemoTokenProvider(RestTemplateBuilder restTemplate) {
        this.restTemplate = restTemplate.build();
    }


    public ResponseEntity<String> getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(login, password);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        return restTemplate.postForEntity(tokenUri, entity, String.class);
    }
}
