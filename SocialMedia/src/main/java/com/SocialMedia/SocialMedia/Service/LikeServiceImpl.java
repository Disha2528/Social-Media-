package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.Entities.Like;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Repository.LikeRepo;
import com.SocialMedia.SocialMedia.Repository.PostRepo;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeRepo likeRepo;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    @Autowired
    private PostRepo postRepo;

    @Override
    public void addLike(String postId) throws EntityNotFoundException {
        Post post = postRepo.getPost(postId);
        if(post == null) throw new EntityNotFoundException("Post not found");

        String userId= authUtil.getCurrentUser();
        Like exists= likeRepo.getLike(userId,postId);

        if(exists!=null){
            return;
        }

        Like like= new Like();
        like.setLikerId(userId);
        like.setPostId(postId);

        post.setLikes(post.getLikes()+1);
        postRepo.savePost(post);

        likeRepo.addLike(like);
    }

    @Override
    public void removeLike(String postId) throws EntityNotFoundException {
        Post post = postRepo.getPost(postId);

        if(post==null) throw new EntityNotFoundException("Post Not found");

        String userId= authUtil.getCurrentUser();
        Like like = likeRepo.getLike(userId,postId);

        if(like==null){
            return;
        }

        likeRepo.removeLike(like);

        int likes= post.getLikes();
        if(likes>0){
            post.setLikes(likes-1);
        }

        postRepo.savePost(post);

    }

    @Override
    public PaginatedResult<Like> getLikes(String postId, int limit, String lastEvaluatedKey) throws EntityNotFoundException {

        Post post = postRepo.getPost(postId);
        if(post==null) throw  new EntityNotFoundException("Post not found");

        Map<String, AttributeValue> startKey= null;
        if(lastEvaluatedKey!=null && !lastEvaluatedKey.isEmpty()){
            startKey= new HashMap<>();
            startKey.put("postId", new AttributeValue().withS(postId));
            startKey.put("likerId", new AttributeValue().withS(lastEvaluatedKey));
        }

        QueryResultPage<Like> queryResultPage= likeRepo.getLikes(postId, limit,startKey);

        List<Like> likes= queryResultPage.getResults();

        String nextlastEvaluatedKey= null;
        Map<String, AttributeValue> lastKeyMap= queryResultPage.getLastEvaluatedKey();
        if(lastKeyMap!=null && lastKeyMap.containsKey("likerId")){
            nextlastEvaluatedKey= lastKeyMap.get("likerId").getS();
        }

        return new PaginatedResult<>(likes,nextlastEvaluatedKey);

    }




}
