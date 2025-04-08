package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.Entities.Friend;
import com.SocialMedia.SocialMedia.Entities.Post;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FeedServiceImpl implements FeedService{

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private FriendService friendService;

    @Autowired
    private PostService postService;


    //get all friends
    @Override
    public List<Friend> getAllFriends(int pageSize) {
        List<Friend> allFriends = new ArrayList<>();
        String lastEvaluatedKey = null;

        do {
            PaginatedResult<Friend> result = friendService.viewFriends(pageSize, lastEvaluatedKey);
            allFriends.addAll(result.getItems());
            lastEvaluatedKey = result.getLastEvaluatedKey();
        } while (lastEvaluatedKey != null);

        return allFriends;
    }


    //get friendpost
    @Override
    public List<Post> getAllFriendsPosts(int friendPostLimit) {
        List<Friend> allFriends = getAllFriends(50);

        List<Post> allPosts = new ArrayList<>();

        for (Friend friend : allFriends) {
            String friendUserName = friend.getFriendName();
            PaginatedResult<Post> result = postService.getPostsByUserName(friendUserName, friendPostLimit, null);
            allPosts.addAll(result.getItems());
        }

        allPosts.sort((p1, p2) -> p2.getPostDate().compareTo(p1.getPostDate()));

        return allPosts;
    }

}
