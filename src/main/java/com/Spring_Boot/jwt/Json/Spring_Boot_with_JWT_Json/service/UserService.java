package com.Spring_Boot.jwt.Json.Spring_Boot_with_JWT_Json.service;

import com.Spring_Boot.jwt.Json.Spring_Boot_with_JWT_Json.model.User;
import com.Spring_Boot.jwt.Json.Spring_Boot_with_JWT_Json.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    //here we can do AutoWiring on field level Yaa On Constructor Level

    private UserRepository userRepository;
    private TokenService tokenService;

    /*
    //Initially Yeah Tha
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }
    */

    @Autowired
    public UserService(UserRepository userRepository,TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService=tokenService;
    }

    public User getUser(ObjectId userid){
        Optional<User> result = userRepository.findById(userid);
        return result.orElseGet(result::get);
    }

    /*
    //This is method for saving User Without Token
    public String saveUser(User user){

        userRepository.save(user);
        return "Successfully Created User";
    }*/

    //In this after saving user, Token is generated
    public String saveUser(User user){

        User savedUser= userRepository.save(user);
        return "\"message\": \"Successfully Created User\",\n" +
                "\"User Data\" :\n\tId : "+ user.getId()+"\n\tName : "+user.getName()+"\n\tEmail : "+user.getEmail()+"\n\tPassword : "+user.getPassword()+",\n"+
                 "\"token\": " +
        tokenService.createToken(savedUser.getId())   ;
    }
//hi
}
