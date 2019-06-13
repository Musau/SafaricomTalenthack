package com.example.safaricomtalenthack.controller;


import com.example.safaricomtalenthack.config.TokenUtil;
import com.example.safaricomtalenthack.model.TokenRequest;
import com.example.safaricomtalenthack.model.TokenResponse;
import com.example.safaricomtalenthack.model.UserModel;
import com.example.safaricomtalenthack.repository.UserRepository;
import com.example.safaricomtalenthack.service.TokenUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class TokenAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUtil jwtTokenUtil;

    @Autowired
    private TokenUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder bcryptEncorder;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody TokenRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(token));
    }

    //Register User
    @PostMapping(value = "/register")
    public UserModel registerUser(@Valid @RequestBody UserModel userModel){
        userModel.setLogin(userModel.getLogin());
        userModel.setPassword(bcryptEncorder.encode(userModel.getPassword()));
        return userRepository.save(userModel);

    }
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }



}
