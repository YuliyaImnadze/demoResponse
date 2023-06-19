package com.example.demoresponse.rest.client.restdemo.v1.client;


import com.example.demoresponse.rest.client.restdemo.RestDemoRestComponent;
import com.example.demoresponse.rest.client.restdemo.v1.dto.UserDTOV1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class UserDTOClientV1 {

    @Value("${rest.client.restdemo.base.url}")
    private String baseUrl;

    @Value("${rest.client.restdemo.base.url}${rest.client.restdemo.byId}")
    private String personById;

    @Value("${rest.client.restdemo.base.url}${rest.client.restdemo.create}")
    private String personCreate;

    @Value("${rest.client.restdemo.base.url}${rest.client.restdemo.update}")
    private String personUpdate;

    @Value("${rest.client.restdemo.base.url}${rest.client.restdemo.delete}")
    private String personDelete;

    @Value("${rest.client.restdemo.base.url}${rest.client.restdemo.byNickname}")
    private String personByNickname;

    @Value("${rest.client.restdemo.base.url}${rest.client.restdemo.byEmail}")
    private String personByEmail;

    private final RestTemplate restTemplate;
    private final RestDemoRestComponent restDemoRestComponent;

    public UserDTOClientV1(RestTemplateBuilder restTemplate, RestDemoRestComponent restDemoRestComponent) {
        this.restTemplate = restTemplate.build();
        this.restDemoRestComponent = restDemoRestComponent;
    }


    public ResponseEntity<UserDTOV1[]> findAll() {
        HttpHeaders restDemoHttpHeaders = restDemoRestComponent.getRestDemoHttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(restDemoHttpHeaders);
        return restTemplate.exchange(baseUrl, HttpMethod.GET, entity, UserDTOV1[].class);
    }


    public ResponseEntity<UserDTOV1> findById(Integer id) {
        HttpHeaders restDemoHttpHeaders = restDemoRestComponent.getRestDemoHttpHeaders();
        HttpEntity<?> entity = new HttpEntity<>(restDemoHttpHeaders);
        return restTemplate.exchange(personById, HttpMethod.GET, entity, UserDTOV1.class, id);
    }


    public ResponseEntity<UserDTOV1> save(UserDTOV1 userDTOV1) {
        HttpHeaders restDemoHttpHeaders = restDemoRestComponent.getRestDemoHttpHeaders();
        restDemoHttpHeaders.setContentType(MediaType.APPLICATION_JSON);
        restDemoHttpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDTOV1> entity = new HttpEntity<>(userDTOV1, restDemoHttpHeaders);
        return restTemplate.exchange(personCreate, HttpMethod.POST, entity, UserDTOV1.class);
    }

    public ResponseEntity<UserDTOV1> edit(UserDTOV1 userDTOV1) {
        HttpHeaders restDemoHttpHeaders = restDemoRestComponent.getRestDemoHttpHeaders();
        restDemoHttpHeaders.setContentType(MediaType.APPLICATION_JSON);
        restDemoHttpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<UserDTOV1> entity = new HttpEntity<>(userDTOV1, restDemoHttpHeaders);
        return restTemplate.exchange(personUpdate, HttpMethod.PUT, entity, UserDTOV1.class);
    }


    public ResponseEntity<UserDTOV1> delete(UserDTOV1 userDTOV1) {
        HttpHeaders restDemoHttpHeaders = restDemoRestComponent.getRestDemoHttpHeaders();
        HttpEntity<UserDTOV1> entity = new HttpEntity<>(userDTOV1, restDemoHttpHeaders);
        return restTemplate.exchange(personDelete, HttpMethod.DELETE, entity, UserDTOV1.class);
    }

    public ResponseEntity<UserDTOV1> findByNickname(UserDTOV1 userDTOV1) {
        HttpHeaders restDemoHttpHeaders = restDemoRestComponent.getRestDemoHttpHeaders();
        HttpEntity<UserDTOV1> entity = new HttpEntity<>(userDTOV1, restDemoHttpHeaders);
        return restTemplate.exchange(personByNickname, HttpMethod.POST, entity,
                UserDTOV1.class);
    }

    public ResponseEntity<UserDTOV1> findByEmail(UserDTOV1 userDTOV1) {
        HttpHeaders restDemoHttpHeaders = restDemoRestComponent.getRestDemoHttpHeaders();
        HttpEntity<UserDTOV1> entity = new HttpEntity<>(userDTOV1, restDemoHttpHeaders);
        return restTemplate.exchange(personByEmail, HttpMethod.POST, entity, UserDTOV1.class);
    }

}
