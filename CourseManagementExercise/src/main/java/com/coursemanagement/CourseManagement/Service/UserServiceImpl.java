package com.coursemanagement.CourseManagement.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.coursemanagement.CourseManagement.DTO.UserDTO;
import com.coursemanagement.CourseManagement.Exception.BadRequestException;
import com.coursemanagement.CourseManagement.Entity.User;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import com.coursemanagement.CourseManagement.Repository.UserRepo;
import com.coursemanagement.CourseManagement.Util.AuthUtil;
import com.coursemanagement.CourseManagement.Util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AuthUtil authUtil;

    //create
    @Override
    public User addUser(UserDTO userDTO) throws BadRequestException{

        if( userRepo.getUserByemail(userDTO.getEmail()) !=null ) throw new BadRequestException("Email is already registered");
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepo.addUser(userDTO);
    }


    //retrieve
    @Override
    public String login(UserDTO userDTO) throws EntityNotFoundException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid email or password");
        }

        User user = userRepo.getUserByemail(userDTO.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + userDTO.getEmail());
        }

        return jwtUtil.generateToken(user.getEmail(), user.getRole());
    }



}
