package com.nicolas.myEcommerce.controller;

import com.nicolas.myEcommerce.dto.UserDTO;
import com.nicolas.myEcommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private UserService service;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String registerUser(@RequestBody UserDTO user){
        try{
            UserDTO userDTO = service.register(user);
            if (userDTO.getId() > 0) {
                return "Given user details";
            }
        }catch (Exception e){
            return "Exception occurred due to" + e.getMessage();
        }
        return null;
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(@RequestParam("page") int pageIndex){
        return service.findAll(pageIndex);
    }

    /*@GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO login(Authentication authentication){
        return service.findByEmail(authentication.getName());
    }
*/
    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO update(@RequestBody UserDTO userToUpdate, @PathVariable("id") long id){
        return service.update(userToUpdate, id);
    }

    @DeleteMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO delete(@PathVariable("id") long id){
        return service.delete(id);
    }

    @GetMapping("/user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getById(@PathVariable("id") long id){
        return service.getById(id);
    }
}
