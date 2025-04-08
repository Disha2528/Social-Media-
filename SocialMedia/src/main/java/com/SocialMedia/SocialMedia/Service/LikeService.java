package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.Entities.Like;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import org.springframework.stereotype.Service;

@Service
public interface LikeService {
    Like addLike(String postId);

    Like removeLike(String postId);

    PaginatedResult<Like> getLikes(String postId, int limit, String lastEvaluatedKey);
}
