package com.example.demoresponse.rest.client.restdemo.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTOV1 implements Serializable {

    private Integer id;

    @Size(min = 2, max = 20, message = "Имя не должно быть короче 2 и длинее 20 символов")
    private  String name;

    @Min(value = 1, message = "Возраст должен быть не менее 1 года")
    @Max(value = 100, message = "Возраст должен быть не более 100 лет")
    private  Integer age;

    @Email
    private  String email;

    @Size(min = 2, max = 20, message = "Ник не должно быть короче 2 и длинее 20 символов")
    private  String nickname;

    public UserDTOV1() {
    }

    public UserDTOV1(String name, Integer age, String email, String nickname) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.nickname = nickname;
    }
}
