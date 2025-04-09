package com.SocialMedia.SocialMedia.Repository;

import com.SocialMedia.SocialMedia.Entities.Like;
import com.SocialMedia.SocialMedia.Entities.Post;
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
public class LikeRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    @Autowired
    private ModelMapper modelMapper;

    public void addLike(Like like){
        dynamoDBMapper.save(like);
    }

    public void removeLike(Like like){
        dynamoDBMapper.delete(like);
    }

    public QueryResultPage<Like> getLikes(String postId, int limit, Map<String,AttributeValue> exclusiveStartKey){
        Map<String, AttributeValue> expressionValue = new HashMap<>();
        expressionValue.put(":postId", new AttributeValue().withS(postId));

        DynamoDBQueryExpression<Like> queryExpression= new DynamoDBQueryExpression<Like>()
                .withKeyConditionExpression("postId = :postId")
                .withExpressionAttributeValues(expressionValue)
                .withLimit(limit);

        if(exclusiveStartKey!=null){
            queryExpression.setExclusiveStartKey(exclusiveStartKey);
        }

        return dynamoDBMapper.queryPage(Like.class, queryExpression);
    }

    public Like getLike(String userId, String postId){
        return dynamoDBMapper.load(Like.class, userId, postId);
    }
}
