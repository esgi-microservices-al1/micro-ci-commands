package com.esgi.microservices.controllers;

import com.esgi.microservices.models.User;
import com.esgi.microservices.services.models.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/add")
    public void adduser(@Valid @RequestBody User user){
         userService.addUsers(user);
    }

    @GetMapping("/users")
    public List<User> findAll(){
        return userService.getUsers();
    }

    @GetMapping("/users/id")
    public User findById(@RequestParam(required=false) long id){
        return userService.getUsersById(id);
    }
}
