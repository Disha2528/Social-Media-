package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.Entities.Like;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    public void addLike(String postId) throws EntityNotFoundException;

    public void removeLike(String postId) throws EntityNotFoundException;

    public PaginatedResult<Like> getLikes(String postId, int limit, String lastEvaluatedKey) throws EntityNotFoundException;
}
