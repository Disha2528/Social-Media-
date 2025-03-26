package com.springboot.dynamoDB.ExpenseTracker.Service;

import com.springboot.dynamoDB.ExpenseTracker.Entity.User;
import com.springboot.dynamoDB.ExpenseTracker.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        User user= userRepo.getUserById(id);

        if(user==null) throw new UsernameNotFoundException("User Not Found");

        return org.springframework.security.core.userdetails.User.withUsername(user.getId())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}

