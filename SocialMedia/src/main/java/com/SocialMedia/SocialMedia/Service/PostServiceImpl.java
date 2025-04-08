package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.PostDTO;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Repository.PostRepo;
import com.SocialMedia.SocialMedia.Repository.UserRepo;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserProvider;
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
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    @Autowired
    private AuthenticatedUserProvider authenticatedUserProvider;

    @Override
    public Post addpost(PostDTO postDTO){
        String userName= authUtil.getCurrentUser();
        postDTO.setUserName(userName);
        return postRepo.addPost(postDTO);
    }

    //current users posts
    @Override
    public PaginatedResult<Post> getUserPosts(int limit, String lastEvaluatedPostId) {
        String userName = authUtil.getCurrentUser();


        Map<String, AttributeValue> startKey = null;
        if (lastEvaluatedPostId != null && !lastEvaluatedPostId.isEmpty()) {
            startKey = new HashMap<>();
            startKey.put("userName", new AttributeValue().withS(userName));
            startKey.put("postId", new AttributeValue().withS(lastEvaluatedPostId));
        }

        QueryResultPage<Post> queryResultPage = postRepo.getUserPosts(userName, limit, startKey);

        List<Post> posts = queryResultPage.getResults();

        String nextLastEvaluatedPostId = null;
        Map<String, AttributeValue> lastKeyMap = queryResultPage.getLastEvaluatedKey();
        if (lastKeyMap != null && lastKeyMap.containsKey("postId")) {
            nextLastEvaluatedPostId = lastKeyMap.get("postId").getS();
        }

        return new PaginatedResult<>(posts, nextLastEvaluatedPostId);
    }


    //get posts of a user by username
    @Override
    public PaginatedResult<Post> getPostsByUserName(String userName, int limit, String lastEvaluatedPostId) {

        Map<String, AttributeValue> startKey = null;
        if (lastEvaluatedPostId != null && !lastEvaluatedPostId.isEmpty()) {
            startKey = new HashMap<>();
            startKey.put("userName", new AttributeValue().withS(userName));
            startKey.put("postId", new AttributeValue().withS(lastEvaluatedPostId));
        }

        QueryResultPage<Post> queryResultPage = postRepo.getUserPosts(userName, limit, startKey);
        List<Post> posts = queryResultPage.getResults();

        String nextLastEvaluatedPostId = null;
        Map<String, AttributeValue> lastKeyMap = queryResultPage.getLastEvaluatedKey();
        if (lastKeyMap != null && lastKeyMap.containsKey("postId")) {
            nextLastEvaluatedPostId = lastKeyMap.get("postId").getS();
        }

        return new PaginatedResult<>(posts, nextLastEvaluatedPostId);
    }

    @Override
    public Post updatePost(PostDTO postDTO){
        return postRepo.updatePost(postDTO);
    }

    @Override
    public Post deletePost(String postId){
        return postRepo.deletePost(postId);
    }



}
