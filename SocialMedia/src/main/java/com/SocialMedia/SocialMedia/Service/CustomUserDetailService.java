package com.SocialMedia.SocialMedia.Service;



import com.SocialMedia.SocialMedia.Entities.User;
import com.SocialMedia.SocialMedia.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        User user = null;
        user = userRepo.getUserByUsername(userName);
        if (user == null) throw new UsernameNotFoundException("User Not Found");

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUserName())
                .password(user.getPassword())
                .build();

    }
}

