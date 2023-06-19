package com.example.demoresponse.rest.client.restdemo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class RestDemoRestComponent {

    public String restToken;

    public HttpHeaders getRestDemoHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(restToken);
        return headers;
    }

}
