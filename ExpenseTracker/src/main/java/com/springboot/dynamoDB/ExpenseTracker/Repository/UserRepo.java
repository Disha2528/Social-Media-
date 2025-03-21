package com.springboot.dynamoDB.ExpenseTracker.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.springboot.dynamoDB.ExpenseTracker.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public User addUser(User user){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);
        dynamoDBMapper.save(user);
        return user;
    }

    public List<User> getUsers(){
        return dynamoDBMapper.scan(User.class, new DynamoDBScanExpression());
    }

    public User getUserById(String id){
        return dynamoDBMapper.load(User.class,id);
    }

    public User getUserByEmail(String email){
        return dynamoDBMapper.load(User.class,email);
    }

    //update method in service

    public void deleteUser(User user){
        dynamoDBMapper.delete(user);
    }

}
