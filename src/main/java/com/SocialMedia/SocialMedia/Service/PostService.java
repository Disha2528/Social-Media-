package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.PostDTO;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import jakarta.validation.constraints.NotNull;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface PostService {
    public PostDTO addpost(PostDTO postDTO) throws IOException;

    public PaginatedResult<Post> getUserPosts(int limit, String lastEvaluatedKey);

    //get posts of a user by username
    public PaginatedResult<Post> getPostsByUserName(String userName, int limit, String lastEvaluatedPostId);

    public PostDTO updatePost(PostDTO postDTO) throws EntityNotFoundException;

    public PostDTO deletePost(String postId) throws EntityNotFoundException, BadRequestException;
}
