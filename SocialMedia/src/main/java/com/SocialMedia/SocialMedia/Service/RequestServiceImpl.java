package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.FriendDTO;
import com.SocialMedia.SocialMedia.DTO.RequestDTO;
import com.SocialMedia.SocialMedia.Entities.Friend;
import com.SocialMedia.SocialMedia.Entities.Request;
import com.SocialMedia.SocialMedia.Entities.User;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Repository.FriendRepo;
import com.SocialMedia.SocialMedia.Repository.RequestRepo;
import com.SocialMedia.SocialMedia.Repository.UserRepo;
import com.SocialMedia.SocialMedia.Static.Status;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RequestServiceImpl implements RequestService{

    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FriendRepo friendRepo;

    @Override
    public Request sendRequest(RequestDTO requestDTO) throws EntityNotFoundException {
        User user = userRepo.getUserByUsername(requestDTO.getReceiverId());
        if(user==null) throw  new EntityNotFoundException("User Not Found");
        requestDTO.setSenderId(authUtil.getCurrentUser());
        requestDTO.setStatus(Status.Pending);
        requestDTO.setSentTime(LocalDateTime.now());
        return requestRepo.createRequest(requestDTO);
    }

    @Override
    public Request withdrawRequest(String receiverId){

        String senderId= authUtil.getCurrentUser();
        return requestRepo.deleteRequest(senderId, receiverId);
    }

    @Override
    public PaginatedResult<Request> viewRequests(int limit, String lastEvaluatedKey) {
        String receiverId = authUtil.getCurrentUser();

        Map<String, AttributeValue> startKey = null;
        if (lastEvaluatedKey != null && !lastEvaluatedKey.isEmpty()) {
            startKey = new HashMap<>();
            startKey.put("receiverId", new AttributeValue().withS(receiverId));
            startKey.put("senderId", new AttributeValue().withS(lastEvaluatedKey)); // assuming senderId is used to paginate
        }

        QueryResultPage<Request> resultPage = requestRepo.getRequestsByReceiverId(receiverId, limit, startKey);

        List<Request> requests = resultPage.getResults();

        String nextToken = null;
        Map<String, AttributeValue> lastKey = resultPage.getLastEvaluatedKey();
        if (lastKey != null && lastKey.containsKey("senderId")) {
            nextToken = lastKey.get("senderId").getS();
        }

        return new PaginatedResult<>(requests, nextToken);
    }


    @Override
    public Request resolveRequest(String senderId, Status status) {
        String receiverId = authUtil.getCurrentUser();
        Request request = requestRepo.getreceivedRequest(senderId);

        if (request == null) {
            throw new RuntimeException("Request not found");
        }

        if (status == Status.Accept) {
            FriendDTO friendDTO= new FriendDTO(senderId,receiverId,LocalDateTime.now());
            FriendDTO friendDTO1= new FriendDTO(receiverId,senderId,LocalDateTime.now());
            Friend friend = friendRepo.addFriend(friendDTO);
            Friend friend1= friendRepo.addFriend(friendDTO1);
            requestRepo.deleteRequest(senderId,receiverId); // Remove request after acceptance
        } else if (status == Status.Reject) {
            request.setStatus(Status.Reject);
            requestRepo.deleteRequest(senderId,receiverId);
        }
        return request;
    }
}

