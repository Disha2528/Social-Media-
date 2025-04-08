package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.CommentDTO;
import com.SocialMedia.SocialMedia.Entities.Comment;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Repository.CommentRepo;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public Comment addComment(CommentDTO commentDTO){
        Comment comment= new Comment();
        comment.setCommentorId(authUtil.getCurrentUser());
        return commentRepo.addComment(commentDTO);
    }

    @Override
    public Comment removeComment(String commentId){
        return commentRepo.removeComment(commentId);
    }

    @Override
    public PaginatedResult<Comment> getComments(String postId, int limit, String lastEvaluatedKey){

        String post = postId;

        Map<String, AttributeValue> startKey= null;
        if(lastEvaluatedKey!=null && !lastEvaluatedKey.isEmpty()){
            startKey= new HashMap<>();
            startKey.put("postId", new AttributeValue().withS(postId));
            startKey.put("commentId", new AttributeValue().withS(lastEvaluatedKey));
        }

        QueryResultPage<Comment> queryResultPage= commentRepo.getComments(postId,limit, startKey);

        List<Comment> comments= queryResultPage.getResults();

        String nextLastEvaluatedKey= null;

        Map<String,AttributeValue> lastKeyMap= queryResultPage.getLastEvaluatedKey();
        if(lastKeyMap!=null && lastKeyMap.containsKey("commentId")){
            nextLastEvaluatedKey=lastKeyMap.get("commentId").getS();
        }

        return  new PaginatedResult<>(comments, nextLastEvaluatedKey);
    }

    @Override
    public Comment updateComment(CommentDTO commentDTO) throws EntityNotFoundException {
        return commentRepo.updateComment(commentDTO);
    }
}
