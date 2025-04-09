package com.SocialMedia.SocialMedia.Repository;

import com.SocialMedia.SocialMedia.DTO.PostDTO;
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
public class PostRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    @Autowired
    private UserRepo userRepo;

    private Map<String, AttributeValue> lastEvaluatedKey;

    public Post addPost(Post post){
        dynamoDBMapper.save(post);
        return post;
    }


    public QueryResultPage<Post> getUserPosts(String userName, int limit, Map<String, AttributeValue> exclusiveStartKey) {
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":userName", new AttributeValue().withS(userName));

        DynamoDBQueryExpression<Post> queryExpression = new DynamoDBQueryExpression<Post>()
                .withIndexName("userPostIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("userName = :userName")
                .withExpressionAttributeValues(expressionValues)
                .withLimit(limit);

        if (exclusiveStartKey != null) {
            queryExpression.setExclusiveStartKey(exclusiveStartKey);
        }

        return dynamoDBMapper.queryPage(Post.class, queryExpression);
    }

    public Post deletePost(String postId){
            Post post= dynamoDBMapper.load(Post.class,postId);
            dynamoDBMapper.delete(post);
            return post;
    }

    public Post getPost(String postId){
        return dynamoDBMapper.load(Post.class,postId);
    }

    public Post savePost(Post post){
        dynamoDBMapper.save(post);
        return post;
    }

}
