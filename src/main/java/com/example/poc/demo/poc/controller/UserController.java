package com.example.poc.demo.poc.controller;

import com.example.poc.demo.poc.common.dto.response.RestResponse;
import com.example.poc.demo.poc.model.User;
import com.example.poc.demo.poc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("v1/public/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public RestResponse saveUser(@Validated @RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/all")
    public RestResponse getAllUser(){
        List<User> userList = userService.getAll();
        return RestResponse.of(userList);
    }

    @PostMapping("/update")
    public RestResponse updateUser(@Validated @RequestBody User user){
        return userService.updateUser(user);
    }

    @PostMapping("/delete")
    public RestResponse deleteUserByEmail(@Validated @RequestParam String email){
        return userService.deleteUserByEmail(email);
    }

    @GetMapping("/delete/all")
    public RestResponse deleteAllUsers(){
        return userService.deleteAllUsers();
    }

    @GetMapping("/find")
    public RestResponse findUserByEmail(@Validated @RequestParam String email){
        return userService.findUserByEmail(email);
    }
}
