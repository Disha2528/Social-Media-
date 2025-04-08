package com.SocialMedia.SocialMedia.Repository;

import com.SocialMedia.SocialMedia.DTO.CommentDTO;
import com.SocialMedia.SocialMedia.Entities.Comment;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CommentRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ModelMapper modelMapper;

    public Comment addComment(CommentDTO commentDTO){
        Comment comment=modelMapper.map(commentDTO, Comment.class);
        dynamoDBMapper.save(comment);
        Post post = dynamoDBMapper.load(Post.class, comment.getPostId());
        post.setComments(post.getComments()+1);
        dynamoDBMapper.save(post);
        return comment;
    }

    public Comment removeComment(String commentId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":commentId", new AttributeValue().withS(commentId));

        DynamoDBQueryExpression<Comment> queryExpression = new DynamoDBQueryExpression<Comment>()
                .withKeyConditionExpression("commentId = :commentId")
                .withExpressionAttributeValues(eav)
                .withIndexName("commentIdIndex")
                .withConsistentRead(false);

        PaginatedQueryList<Comment> comments = dynamoDBMapper.query(Comment.class, queryExpression);
        if (comments.isEmpty()) {
            throw new RuntimeException("Comment not found");
        }

        Comment comment = comments.get(0);
        dynamoDBMapper.delete(comment);

        Post post = dynamoDBMapper.load(Post.class, comment.getPostId());
        if (post != null) {
            post.setComments(post.getComments() - 1);
            dynamoDBMapper.save(post);
        }

        return comment;
    }


    public QueryResultPage<Comment> getComments(String postId, int limit, Map<String, AttributeValue> exclusiveStartKey){
        Map<String,AttributeValue> expressionValue= new HashMap<>();
        expressionValue.put(":postId",new AttributeValue().withS(postId));

        DynamoDBQueryExpression<Comment> queryExpression= new DynamoDBQueryExpression<Comment>()
                .withKeyConditionExpression("postId = :postId")
                .withExpressionAttributeValues(expressionValue)
                .withLimit(limit);

        if(exclusiveStartKey!=null){
            queryExpression.setExclusiveStartKey(exclusiveStartKey);
        }

        return dynamoDBMapper.queryPage(Comment.class, queryExpression);

    }

    public Comment updateComment(CommentDTO commentDTO) throws EntityNotFoundException {
        Comment comment= dynamoDBMapper.load(Comment.class, commentDTO.getPostId(), commentDTO.getCommentId());
        if(comment==null){
            throw new EntityNotFoundException("Comment not found");
        }
        modelMapper.map(commentDTO,comment);
        dynamoDBMapper.save(comment);
        return comment;
    }
}
