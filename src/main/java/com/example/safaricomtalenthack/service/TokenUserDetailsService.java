package com.example.safaricomtalenthack.service;



import com.example.safaricomtalenthack.repository.UserRepository;
import com.example.safaricomtalenthack.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/*JWTUserDetailsService implements the Spring Security UserDetailsService interface.
It overrides the loadUserByUsername for fetching user details from the database using the username.*/
@Service
public class TokenUserDetailsService implements UserDetailsService {

   // @Autowired
   // private UserRepository userRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserModel userModel=userRepository.loadByUsername(username);
        if (userModel==null){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(userModel.getLogin(),userModel.getPassword(),new ArrayList<>());

      /*  if ("hackathon".equals(username)) {
            return new User("hackathon", "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
                    new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }*/
    }
}
