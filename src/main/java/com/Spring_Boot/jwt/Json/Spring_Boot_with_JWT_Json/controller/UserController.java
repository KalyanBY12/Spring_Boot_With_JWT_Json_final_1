package com.Spring_Boot.jwt.Json.Spring_Boot_with_JWT_Json.controller;

import com.Spring_Boot.jwt.Json.Spring_Boot_with_JWT_Json.model.User;
import com.Spring_Boot.jwt.Json.Spring_Boot_with_JWT_Json.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService=userService;
    }

    @PostMapping("/saveUser")
    public String savingUser(@RequestBody User user)
    {
        return userService.saveUser(user);
    }



    //Method-1
    @GetMapping("/getUserMethod1/{userid}")
    public User getUserDetailsMethod1(@PathVariable ObjectId userid )
    {
       // ObjectId userId=(ObjectId)httpServletRequest.getAttribute("userId" );
        return userService.getUser(userid);
    }

    //Method-2
    @GetMapping("/getUser")
    public User getUserDetailsMethod2(HttpServletRequest httpServletRequest)
    {
        ObjectId userId=(ObjectId)httpServletRequest.getAttribute("userId" );
        return userService.getUser(userId);
    }






}
