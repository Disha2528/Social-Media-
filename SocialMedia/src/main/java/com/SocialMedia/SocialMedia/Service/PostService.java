package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.PostDTO;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public interface PostService {
    public Post addpost(PostDTO postDTO);

    public PaginatedResult<Post> getUserPosts(int limit, String lastEvaluatedKey);

    //get posts of a user by username
    PaginatedResult<Post> getPostsByUserName(String userName, int limit, String lastEvaluatedPostId);

    public Post updatePost(PostDTO postDTO);

    public Post deletePost(String postId);
}
