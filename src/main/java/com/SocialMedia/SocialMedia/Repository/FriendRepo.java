package com.SocialMedia.SocialMedia.Repository;

import com.SocialMedia.SocialMedia.DTO.FriendDTO;
import com.SocialMedia.SocialMedia.Entities.Friend;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FriendRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    public Friend addFriend(Friend friend){
        dynamoDBMapper.save(friend);
        return friend;
    }

    public Friend deleteFriend(String friendName){
        String userName= authUtil.getCurrentUser();
        Friend friend= dynamoDBMapper.load(Friend.class, userName,friendName);
        Friend friend1= dynamoDBMapper.load(Friend.class,friendName,userName);
        dynamoDBMapper.delete(friend1);
        dynamoDBMapper.delete(friend);
        return friend;
    }

    public QueryResultPage<Friend> viewFriends(String userName, int limit, Map<String, AttributeValue> exclusiveStartKey){
        Map<String, AttributeValue> expressionValue= new HashMap<>();
        expressionValue.put(":userName",new AttributeValue().withS(userName));

        DynamoDBQueryExpression<Friend> queryExpression= new DynamoDBQueryExpression<Friend>()
                .withKeyConditionExpression("userName = :userName")
                .withExpressionAttributeValues(expressionValue)
                .withLimit(limit);

        if(exclusiveStartKey!=null){
            queryExpression.setExclusiveStartKey(exclusiveStartKey);
        }

        return  dynamoDBMapper.queryPage(Friend.class,queryExpression);

    }

    public Friend getFriend(String userName, String friendName){
        return dynamoDBMapper.load(Friend.class, userName, friendName);
    }





}
