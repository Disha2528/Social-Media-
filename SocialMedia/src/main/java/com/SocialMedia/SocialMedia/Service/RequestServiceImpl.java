package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.FriendDTO;
import com.SocialMedia.SocialMedia.DTO.RequestDTO;
import com.SocialMedia.SocialMedia.Entities.Friend;
import com.SocialMedia.SocialMedia.Entities.Request;
import com.SocialMedia.SocialMedia.Entities.User;
import com.SocialMedia.SocialMedia.Exceptions.EntityAlreadyExistsException;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Repository.FriendRepo;
import com.SocialMedia.SocialMedia.Repository.RequestRepo;
import com.SocialMedia.SocialMedia.Repository.UserRepo;
import com.SocialMedia.SocialMedia.Static.Status;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public RequestDTO sendRequest(RequestDTO requestDTO) throws EntityNotFoundException {
        String userId= authUtil.getCurrentUser();

        Request request= modelMapper.map(requestDTO,Request.class);

        User user = userRepo.getUserByUsername(requestDTO.getReceiverId());
        if(user==null) throw  new EntityNotFoundException("User Not Found");

        Request request1= requestRepo.getSentRequest(requestDTO.getReceiverId());

        if(friendRepo.getFriend(userId, requestDTO.getReceiverId())!=null){
            throw new EntityAlreadyExistsException("You both are friends already");
        }

        if(request!=null){
            throw new EntityAlreadyExistsException("Already sent Request");
        }

        requestDTO.setSenderId(userId);
        requestDTO.setStatus(Status.Pending);
        requestDTO.setSentTime(LocalDateTime.now());
        requestRepo.createRequest(request);
        return requestDTO;
    }

    @Override
    public RequestDTO withdrawRequest(String receiverId) throws EntityNotFoundException {

        String senderId= authUtil.getCurrentUser();
        Request request= requestRepo.getSentRequest(receiverId);

        if(request==null) throw new EntityNotFoundException("Request not found");
        requestRepo.deleteRequest(senderId, receiverId);
        RequestDTO requestDTO= modelMapper.map(request,RequestDTO.class);
        return requestDTO;

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
    public RequestDTO resolveRequest(String senderId, Status status) throws EntityNotFoundException {
        String receiverId = authUtil.getCurrentUser();
        Request request = requestRepo.getreceivedRequest(senderId);

        if (request == null) {
            throw new EntityNotFoundException("Request not found");
        }

        if (status == Status.Accept) {

            FriendDTO friendDTO1 = new FriendDTO(senderId, receiverId, LocalDateTime.now());
            Friend friend1= modelMapper.map(friendDTO1, Friend.class);
            FriendDTO friendDTO2 = new FriendDTO(receiverId, senderId, LocalDateTime.now());
            Friend friend2= modelMapper.map(friendDTO2, Friend.class);

            friendRepo.addFriend(friend1);
            friendRepo.addFriend(friend2);


            requestRepo.deleteRequest(senderId, receiverId);


            Request reciprocalRequest = requestRepo.getSentRequest(senderId);
            if (reciprocalRequest != null) {
                requestRepo.deleteRequest(receiverId, senderId);
            }

        } else if (status == Status.Reject) {
            request.setStatus(Status.Reject);
            requestRepo.deleteRequest(senderId, receiverId);
        }

        RequestDTO requestDTO= modelMapper.map(request,RequestDTO.class);
        return requestDTO;
    }
}

