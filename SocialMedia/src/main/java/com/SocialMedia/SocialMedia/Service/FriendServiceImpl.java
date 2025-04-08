package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.Entities.Friend;
import com.SocialMedia.SocialMedia.Repository.FriendRepo;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FriendServiceImpl implements FriendService{

    @Autowired
    private FriendRepo friendRepo;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    @Override
    public Friend unfriend(String friendName){
        return friendRepo.deleteFriend(friendName);
    }

    @Override
    public PaginatedResult<Friend> viewFriends(int limit, String lastEvaluatedKey){
        String userName= authUtil.getCurrentUser();

        Map<String, AttributeValue> startKey = null;
        if(lastEvaluatedKey!=null && !lastEvaluatedKey.isEmpty()){
            startKey= new HashMap<>();
            startKey.put("userName", new AttributeValue().withS(userName));
            startKey.put("friendName",new AttributeValue().withS(lastEvaluatedKey));
        }

        QueryResultPage<Friend> queryResultPage= friendRepo.viewFriends(userName, limit,startKey);
        List<Friend> friends = queryResultPage.getResults();

        String nextLastEvaluatedKey= null;
        Map<String, AttributeValue> lastKeyMap= queryResultPage.getLastEvaluatedKey();
        if(lastKeyMap!=null && lastKeyMap.containsKey("friendName")){
            nextLastEvaluatedKey= lastKeyMap.get("friendName").getS();
        }

        return  new PaginatedResult<>(friends,nextLastEvaluatedKey);

    }

}
