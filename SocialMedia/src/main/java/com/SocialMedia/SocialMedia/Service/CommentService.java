package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.CommentDTO;
import com.SocialMedia.SocialMedia.Entities.Comment;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    public CommentDTO addComment(CommentDTO commentDTO) throws EntityNotFoundException;

    public CommentDTO removeComment(String commentId) throws EntityNotFoundException;

    public PaginatedResult<CommentDTO> getComments(String postId, int limit, String lastEvaluatedKey) throws EntityNotFoundException;

    public CommentDTO updateComment(CommentDTO commentDTO) throws EntityNotFoundException;
}
