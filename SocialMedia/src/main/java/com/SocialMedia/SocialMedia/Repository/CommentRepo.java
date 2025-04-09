package com.SocialMedia.SocialMedia.Repository;

import com.SocialMedia.SocialMedia.Entities.Comment;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CommentRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Comment addComment(Comment comment){
        dynamoDBMapper.save(comment);
        return comment;
    }

    public Comment findByCommentId(String commentId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":commentId", new AttributeValue().withS(commentId));

        DynamoDBQueryExpression<Comment> queryExpression = new DynamoDBQueryExpression<Comment>()
                .withKeyConditionExpression("commentId = :commentId")
                .withExpressionAttributeValues(eav)
                .withIndexName("commentIdIndex")
                .withConsistentRead(false);

        PaginatedQueryList<Comment> comments = dynamoDBMapper.query(Comment.class, queryExpression);
        return comments.isEmpty() ? null : comments.get(0);
    }

    public void deleteComment(Comment comment) {
        dynamoDBMapper.delete(comment);
    }

    public Comment updateComment(Comment comment) {
        dynamoDBMapper.save(comment);
        return comment;
    }

    public Comment getComment(String postId, String commentId){
        return dynamoDBMapper.load(Comment.class, postId, commentId);
    }

    public QueryResultPage<Comment> getComments(String postId, int limit, Map<String, AttributeValue> exclusiveStartKey){
        Map<String, AttributeValue> expressionValue = new HashMap<>();
        expressionValue.put(":postId", new AttributeValue().withS(postId));

        DynamoDBQueryExpression<Comment> queryExpression = new DynamoDBQueryExpression<Comment>()
                .withKeyConditionExpression("postId = :postId")
                .withExpressionAttributeValues(expressionValue)
                .withLimit(limit);

        if (exclusiveStartKey != null) {
            queryExpression.setExclusiveStartKey(exclusiveStartKey);
        }

        return dynamoDBMapper.queryPage(Comment.class, queryExpression);
    }

}
