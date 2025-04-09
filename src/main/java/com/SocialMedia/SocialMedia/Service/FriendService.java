package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.FriendDTO;
import com.SocialMedia.SocialMedia.Entities.Friend;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import org.springframework.stereotype.Service;

@Service
public interface FriendService {


    public FriendDTO unfriend(String friendName) throws EntityNotFoundException;

    public PaginatedResult<Friend> viewFriends(int limit, String lastEvaluatedKey);
}
