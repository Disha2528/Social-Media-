package com.springboot.dynamoDB.ExpenseTracker.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.springboot.dynamoDB.ExpenseTracker.DTO.LoginDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.UserDTO;
import com.springboot.dynamoDB.ExpenseTracker.Entity.User;
import com.springboot.dynamoDB.ExpenseTracker.Exceptions.EntityNotFoundException;
import com.springboot.dynamoDB.ExpenseTracker.Repository.UserRepo;
import com.springboot.dynamoDB.ExpenseTracker.Util.JwtUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DynamoDBMapper mapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;



    //create
    @Override
    public User addUser(User user){
        userRepo.addUser(user);
        return user;
    }

    @Override
    public String login(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserId(), loginDTO.getPassword()));
        UserDetails userDetails= userDetailService.loadUserByUsername(loginDTO.getUserId());
        return jwtUtil.generateToken(userDetails.getUsername());

    }

    //retrieve
    @Override
    public List<User> getUsers() throws EntityNotFoundException{

        List<User> userEntities = userRepo.getUsers();

        if(userEntities.isEmpty()) throw new EntityNotFoundException("Entity not found");

        return userEntities;
    }

    //retrieve
    @Override
    public User getUserById(String id) throws EntityNotFoundException{
        User exists= userRepo.getUserById(id);

        if(exists==null) throw new EntityNotFoundException("Entity not found");

        return exists;
    }

    //update
    @Override
    public User updateUser(String id, UserDTO userDTO) throws EntityNotFoundException {
        User exists = userRepo.getUserById(id);

        if (exists == null) throw new EntityNotFoundException("Entity Not Found");

        modelMapper.map(userDTO, exists);

        mapper.save(exists);

        return exists;
    }

    //delete
    @Override
    public User deleteUser(String id) throws EntityNotFoundException{
        User exists= userRepo.getUserById(id);

        if(exists==null) throw new EntityNotFoundException("Entity Not Found");

        userRepo.deleteUser(exists);

        return exists;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

}
