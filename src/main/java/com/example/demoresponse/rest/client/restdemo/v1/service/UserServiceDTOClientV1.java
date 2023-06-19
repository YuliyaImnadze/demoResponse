package com.example.demoresponse.rest.client.restdemo.v1.service;


import com.example.demoresponse.rest.client.restdemo.v1.dto.UserDTOV1;
import com.example.demoresponse.rest.client.restdemo.v1.client.UserDTOClientV1;
import com.example.demoresponse.rest.client.restdemo.v1.dto.constrants.uniquenickname.UniqueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


@Service
public class UserServiceDTOClientV1 {

    private final UserDTOClientV1 userDTOClientV1;
    private final UniqueValidator uniqueValidator;

    @Autowired
    public UserServiceDTOClientV1(UserDTOClientV1 userDTOClientV1,
                                  @Qualifier("uniqueValidator") UniqueValidator uniqueValidator) {
        this.userDTOClientV1 = userDTOClientV1;
        this.uniqueValidator = uniqueValidator;
    }


    public UserDTOV1[] findAll() {
        return userDTOClientV1.findAll().getBody();
    }


    public UserDTOV1 findById(Integer id) {
        return userDTOClientV1.findById(id).getBody();
    }


    public UserDTOV1 save(UserDTOV1 userDTOV1, BindingResult bindingResult) {
        uniqueValidator.validate(userDTOV1, bindingResult);
        if (bindingResult.hasErrors()) {
            return null;
        }
        return userDTOClientV1.save(userDTOV1).getBody();
    }

    public UserDTOV1 edit(UserDTOV1 userDTOV1, BindingResult bindingResult) {
        uniqueValidator.validate(userDTOV1, bindingResult);
        if (bindingResult.hasErrors()) {
            return null;
        }
        return userDTOClientV1.edit(userDTOV1).getBody();
    }


    public void delete(UserDTOV1 userDTOV1) {
        userDTOClientV1.delete(userDTOV1);
    }

    public UserDTOV1 findByNickname(UserDTOV1 userDTOV1) {
        return userDTOClientV1.findByNickname(userDTOV1).getBody();
    }

    public UserDTOV1 findByEmail(UserDTOV1 userDTOV1) {
        return userDTOClientV1.findByEmail(userDTOV1).getBody();
    }

}


