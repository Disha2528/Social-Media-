package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.CommentDTO;
import com.SocialMedia.SocialMedia.Entities.Comment;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Repository.CommentRepo;
import com.SocialMedia.SocialMedia.Repository.PostRepo;
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
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PostRepo postRepo;

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) throws EntityNotFoundException {
        Post post = postRepo.getPost(commentDTO.getPostId());
        if (post == null) throw new EntityNotFoundException("Post not found");
        commentDTO.setCommentorId(authUtil.getCurrentUser());
        Comment comment = commentRepo.addComment(modelMapper.map(commentDTO, Comment.class));
        post.setComments(post.getComments() + 1);
        postRepo.savePost(post);

        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public CommentDTO removeComment(String commentId) throws EntityNotFoundException {
        Comment comment = commentRepo.findByCommentId(commentId);
        if (comment == null) {
            throw new EntityNotFoundException("Comment not found");
        }

        commentRepo.deleteComment(comment);

        Post post = postRepo.getPost(comment.getPostId());
        if (post == null) {
            throw new EntityNotFoundException("Post not found");
        }

        if (post.getComments() > 0) {
            post.setComments(post.getComments() - 1);
        }
        postRepo.savePost(post);

        return modelMapper.map(comment, CommentDTO.class);
    }

    @Override
    public PaginatedResult<CommentDTO> getComments(String postId, int limit, String lastEvaluatedKey) throws EntityNotFoundException {
        String post = postId;
        if (post == null) {
            throw new EntityNotFoundException("Post not found");
        }

        Map<String, AttributeValue> startKey = null;
        if (lastEvaluatedKey != null && !lastEvaluatedKey.isEmpty()) {
            startKey = new HashMap<>();
            startKey.put("postId", new AttributeValue().withS(postId));
            startKey.put("commentId", new AttributeValue().withS(lastEvaluatedKey));
        }

        QueryResultPage<Comment> queryResultPage = commentRepo.getComments(postId, limit, startKey);
        List<CommentDTO> comments = queryResultPage.getResults()
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());

        String nextLastEvaluatedKey = null;
        Map<String, AttributeValue> lastKeyMap = queryResultPage.getLastEvaluatedKey();
        if (lastKeyMap != null && lastKeyMap.containsKey("commentId")) {
            nextLastEvaluatedKey = lastKeyMap.get("commentId").getS();
        }

        return new PaginatedResult<>(comments, nextLastEvaluatedKey);
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO) throws EntityNotFoundException {
        Comment comment = commentRepo.getComment(commentDTO.getPostId(), commentDTO.getCommentId());
        if (comment == null) throw new EntityNotFoundException("Comment does not Exist");
        commentDTO.setCommentorId(authUtil.getCurrentUser());
        modelMapper.map(commentDTO,comment);
        Comment updatedComment = commentRepo.updateComment(comment);
        return modelMapper.map(updatedComment, CommentDTO.class);
    }
}
