package com.coursemanagement.CourseManagement.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.coursemanagement.CourseManagement.DTO.UserDTO;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.coursemanagement.CourseManagement.Entity.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ModelMapper modelMapper;


    //create
    public User addUser( UserDTO userDTO){
        User user= modelMapper.map(userDTO, User.class);
        dynamoDBMapper.save(user);
        return user;
    }

    //retrieve
    public User getUserByemail(String email) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":email", new AttributeValue().withS(email));

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName("emailIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("email = :email")
                .withExpressionAttributeValues(eav);

        List<User> result = dynamoDBMapper.query(User.class, queryExpression);
        if(result.isEmpty()) return null;

        return result.get(0);
    }


    //retrieve
    public User getUserById(String id)  {

         return dynamoDBMapper.load(User.class, id);
    }



}
