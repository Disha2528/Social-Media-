package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.CommentDTO;
import com.SocialMedia.SocialMedia.Entities.Comment;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public Comment addComment(CommentDTO commentDTO);

    public Comment removeComment(String commentId);

    public PaginatedResult<Comment> getComments(String postId, int limit, String lastEvaluatedKey);

    public Comment updateComment(CommentDTO commentDTO) throws EntityNotFoundException;
}
