package com.example.demoresponse.controller;


import com.example.demoresponse.rest.client.restdemo.v1.dto.UserDTOV1;
import com.example.demoresponse.rest.client.restdemo.v1.service.UserServiceDTOClientV1;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@Controller
//@Validated
@RequestMapping("/api/v1/people")
public class UserDTOController {

    private final UserServiceDTOClientV1 service;

    @Autowired
    public UserDTOController(UserServiceDTOClientV1 service) {
        this.service = service;
    }


    @GetMapping("/all")
    public String peopleList(Model model) {
        UserDTOV1[] peopleList = service.findAll();
        model.addAttribute("peopleList", peopleList);
        return "all";
    }

    @GetMapping("/{id}")
    public String personById(@PathVariable("id") Integer id, Model model) {
        UserDTOV1 personById = service.findById(id);
        model.addAttribute("personById", personById);
        return "id";
    }


    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("newUserDTOV1", new UserDTOV1());
        return "new";
    }


    @PostMapping()
    public String addPerson(@ModelAttribute("newUserDTOV1") @Valid UserDTOV1 userDTOV1, BindingResult bindingResult) {
        UserDTOV1 saveUser = service.save(userDTOV1, bindingResult);
        if (Objects.isNull(saveUser) && bindingResult.hasErrors()) {
            return "new";
        }
        return "redirect:/api/v1/people/all";
    }


    @GetMapping("/{id}/edit")
    public String getEditedUser(@PathVariable("id") Integer id, Model model) {
        UserDTOV1 userById = service.findById(id);
        model.addAttribute("editedUser", userById);
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String editUser(@ModelAttribute("editedUser") @Valid UserDTOV1 userDTOV1, BindingResult bindingResult) {
        UserDTOV1 editedUser = service.edit(userDTOV1, bindingResult);
        if (Objects.isNull(editedUser) && bindingResult.hasErrors()) {
            return "edit";
        }

        return "redirect:/api/v1/people/all";
    }


    @GetMapping("{id}/delete")
    public String getDeleteUser(@PathVariable("id") Integer id, Model model) {
        UserDTOV1 userById = service.findById(id);
        model.addAttribute("deletedUser", userById);
        return "delete";
    }


    @PostMapping("/{id}/delete")
    public String deleteUser(@ModelAttribute("deletedUser") @Valid UserDTOV1 userDTOV1) {
        service.delete(userDTOV1);
        return "redirect:/api/v1/people/all";
    }


}
