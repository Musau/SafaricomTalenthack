package com.example.safaricomtalenthack.controller;


import com.example.safaricomtalenthack.repository.UserRepository;
import com.example.safaricomtalenthack.model.UserModel;
import com.example.safaricomtalenthack.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;

@RestController
//@RequestMapping(value = "/movie-api",produces = "application/json")
public class UserController {
    @Autowired
    UserRepository userRepository;

    //get a user
    @GetMapping("/user/{id}")
    public UserModel get(@PathVariable Long id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User","id",id));
    }

    //get all users
    @RequestMapping("/all-users")
    public List< UserModel> getAllUsers(){
        return userRepository.findAll();
    }
    //delete user
    @RequestMapping("/delete-user/{id}")
    public ResponseEntity<?>deleteMovie(@PathVariable("id")long id){
        UserModel userModel=userRepository.findById(id).orElseThrow(()->new UserNotFoundException("Movie","id",id));
        userRepository.delete(userModel);
        return ResponseEntity.ok().build();

    }


}
