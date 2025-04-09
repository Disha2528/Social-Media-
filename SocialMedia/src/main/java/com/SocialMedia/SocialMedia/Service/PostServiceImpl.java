package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.PostDTO;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Entities.User;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Repository.PostRepo;
import com.SocialMedia.SocialMedia.Repository.UserRepo;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.SocialMedia.SocialMedia.Util.FileStorageService;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    private ModelMapper modelMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public PostDTO addpost(PostDTO postDTO) throws IOException {
        String userName= authUtil.getCurrentUser();
        postDTO.setUserName(userName);

        if (postDTO.getPostPath() != null &&  postDTO.getPostName()!=null) {
            fileStorageService.storePostImage(userName, postDTO.getPostPath(), postDTO.getPostName());
            postDTO.setPostPath(userName);
        }

        Post post= modelMapper.map(postDTO, Post.class);
        postRepo.addPost(post);

        User user= userRepo.getUserByUsername(userName);
        user.setPostCount(user.getPostCount()+1);
        userRepo.save(user);

        return postDTO;
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
    public PostDTO updatePost(PostDTO postDTO) throws EntityNotFoundException {
        Post post= postRepo.getPost(postDTO.getPostId());

        if(post==null) throw new EntityNotFoundException("Post not found");

        modelMapper.map(postDTO,post);
        postRepo.savePost(post);

        return postDTO;
    }

    @Override
    public PostDTO deletePost(String postId) throws EntityNotFoundException, BadRequestException {
        Post post = postRepo.getPost(postId);
        String userId= authUtil.getCurrentUser();

        if(post==null) throw new EntityNotFoundException("Post not found");

        if(!userId.equals(post.getUserName())) throw new EntityNotFoundException("No such post exists for you");

        postRepo.deletePost(postId);

        User user= userRepo.getUserByUsername(userId);

        user.setPostCount(user.getPostCount()-1);

        PostDTO postDTO= modelMapper.map(post, PostDTO.class);

        return postDTO;
    }



}
