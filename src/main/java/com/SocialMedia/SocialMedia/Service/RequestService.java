package com.SocialMedia.SocialMedia.Service;

import com.SocialMedia.SocialMedia.DTO.RequestDTO;
import com.SocialMedia.SocialMedia.Entities.Request;
import com.SocialMedia.SocialMedia.Exceptions.EntityNotFoundException;
import com.SocialMedia.SocialMedia.Static.Status;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import org.springframework.stereotype.Service;

@Service
public interface RequestService {
    public RequestDTO sendRequest(RequestDTO requestDTO) throws EntityNotFoundException;

    public RequestDTO withdrawRequest(String receiverId) throws EntityNotFoundException;

    public  PaginatedResult<Request> viewRequests(int limit, String lastEvaluatedKey);

    public RequestDTO resolveRequest(String senderId, Status status) throws EntityNotFoundException;
}
