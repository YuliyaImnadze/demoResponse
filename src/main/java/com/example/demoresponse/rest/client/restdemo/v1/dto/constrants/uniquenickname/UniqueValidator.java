package com.example.demoresponse.rest.client.restdemo.v1.dto.constrants.uniquenickname;


import com.example.demoresponse.rest.client.restdemo.v1.dto.UserDTOV1;
import com.example.demoresponse.rest.client.restdemo.v1.client.UserDTOClientV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Objects;
import java.util.Optional;

@Service
public class UniqueValidator implements Validator {

    private final UserDTOClientV1 userDTOClientV1;

    @Autowired
    public UniqueValidator(UserDTOClientV1 userDTOClientV1) {
        this.userDTOClientV1 = userDTOClientV1;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTOV1.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTOV1 userDTOV1 = (UserDTOV1) target;
        Optional<UserDTOV1> userByNickname = Optional.ofNullable(userDTOClientV1.findByNickname(userDTOV1).getBody());
        if (userByNickname.isPresent()) {
            Integer userId = userByNickname.get().getId();
            if (!Objects.equals(userId, userDTOV1.getId())) {
                errors.rejectValue("nickname", "", "This nickname is already taken");
            }
        }

        if (!userDTOV1.getEmail().equals("")) {
            Optional<UserDTOV1> userByEmail = Optional.ofNullable(userDTOClientV1.findByEmail(userDTOV1).getBody());
            if (userByEmail.isPresent()) {
                Integer userId = userByEmail.get().getId();
                if (!Objects.equals(userId, userDTOV1.getId()) && userId != null) {
                    errors.rejectValue("email", "", "This email is already taken");
                }
            }
        }
    }
}
