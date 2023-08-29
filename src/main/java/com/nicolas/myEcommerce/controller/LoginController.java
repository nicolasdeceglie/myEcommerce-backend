package com.nicolas.myEcommerce.controller;

import com.nicolas.myEcommerce.dto.UserDTO;
import com.nicolas.myEcommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody UserDTO user){
        try{
            UserDTO userDTO = service.register(user);
            if (userDTO.getId() >0) {
                return "Given user details";
            }
        }catch (Exception e){
            return "Exception occurred due to" + e.getMessage();
        }
        return null;
    }
    @GetMapping("/login")
    public UserDTO getUserDetailsAfterLogin(Authentication authentication){
        return service.findByEmail(authentication.getName());
    }
}
