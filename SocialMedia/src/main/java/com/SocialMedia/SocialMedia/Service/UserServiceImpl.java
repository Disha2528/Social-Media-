package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.UserDTO;
import com.SocialMedia.SocialMedia.Entities.User;
import com.SocialMedia.SocialMedia.Exceptions.EntityAlreadyExistsException;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Repository.UserRepo;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.SocialMedia.SocialMedia.Util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User registerUser(UserDTO userDTO) {
       if( userRepo.getUserByUsername(userDTO.getUserName()) !=null){
           throw new EntityAlreadyExistsException("Account with this userName already Exists");
       }

       if(!userRepo.getUserByEmail(userDTO.getEmail()).isEmpty()){
           throw new EntityAlreadyExistsException("Account with this email already exists");
       }
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepo.register(userDTO);
    }

    @Override
    public String login(UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Enter Valid Credentials");
        }

        User user= userRepo.getUserByUsername(userDTO.getUserName());

        return jwtUtil.generateToken(user.getUserName());
    }

    @Override
    public User update(UserDTO userDTO) throws EntityNotFoundException {

        if(userDTO.getPassword()!=null){
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        User user = userRepo.getUserByUsername(authUtil.getCurrentUser());
        if(user == null) throw new EntityNotFoundException("User Not Found");
        modelMapper.map(userDTO,user);
        return userRepo.update(user);
    }




}