package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.Entities.Like;
import com.SocialMedia.SocialMedia.Repository.LikeRepo;
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

    @Override
    public Like addLike(String postId){
        Like like= new Like();
        like.setLikerId(authUtil.getCurrentUser());
        like.setPostId(postId);
        likeRepo.addLike(like);
        return like;
    }

    @Override
    public Like removeLike(String postId){
        return likeRepo.removeLike(postId);
    }

    @Override
    public PaginatedResult<Like> getLikes(String postId, int limit, String lastEvaluatedKey){
        String post= postId;

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
