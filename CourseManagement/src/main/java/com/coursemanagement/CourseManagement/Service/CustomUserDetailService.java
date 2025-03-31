package com.coursemanagement.CourseManagement.Service;


import com.coursemanagement.CourseManagement.Entity.User;
import com.coursemanagement.CourseManagement.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.getUserByemail(email);
        if (user == null) throw new UsernameNotFoundException("User Not Found");

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();

    }
}

