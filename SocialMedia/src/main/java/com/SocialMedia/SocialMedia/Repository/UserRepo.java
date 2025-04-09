package com.SocialMedia.SocialMedia.Repository;

import com.SocialMedia.SocialMedia.DTO.UserDTO;
import com.SocialMedia.SocialMedia.Entities.User;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class UserRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ModelMapper modelMapper;


    public User getUserByUsername(String userName) {
       User user= dynamoDBMapper.load(User.class, userName);
       return user;
    }

    public List<User> getUserByEmail(String email){

        DynamoDBQueryExpression<User> queryExpression = new DynamoDBQueryExpression<User>()
                .withIndexName("emailIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("email = :email")
                .withExpressionAttributeValues(Map.of(":email", new AttributeValue().withS(email)));

       List<User> users = dynamoDBMapper.query(User.class, queryExpression);

       return users;
    }

    public User update(User user){
       dynamoDBMapper.save(user);
        return user;
    }

    public User save(User user){
        dynamoDBMapper.save(user);
        return user;
    }
}
