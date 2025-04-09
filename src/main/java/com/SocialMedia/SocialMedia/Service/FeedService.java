package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.Entities.Friend;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedService {


    //get all friends
    public List<Friend> getAllFriends(int pageSize);

    //get friendpost
    public List<Post> getAllFriendsPosts(int friendPostLimit);
}
