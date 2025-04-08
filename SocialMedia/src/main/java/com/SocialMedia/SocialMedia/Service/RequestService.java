package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.RequestDTO;
import com.SocialMedia.SocialMedia.Entities.Request;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Static.Status;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import org.springframework.stereotype.Service;

@Service
public interface RequestService {
    public Request sendRequest(RequestDTO requestDTO) throws EntityNotFoundException;

    public Request withdrawRequest(String receiverId);

    public  PaginatedResult<Request> viewRequests(int limit, String lastEvaluatedKey);

    public Request resolveRequest(String senderId, Status status);
}
