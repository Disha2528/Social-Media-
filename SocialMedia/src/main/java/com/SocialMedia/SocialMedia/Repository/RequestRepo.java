package com.SocialMedia.SocialMedia.Repository;

import com.SocialMedia.SocialMedia.DTO.RequestDTO;
import com.SocialMedia.SocialMedia.Entities.Request;
import com.SocialMedia.SocialMedia.Util.AuthenticatedUserUtil;
import com.SocialMedia.SocialMedia.Util.PaginatedResult;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RequestRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuthenticatedUserUtil authUtil;

    public Request createRequest(Request request){
        dynamoDBMapper.save(request);
        return  request;
    }

    public Request deleteRequest(String senderId, String receiverId){;
        Request request= dynamoDBMapper.load(Request.class,senderId,receiverId);
        dynamoDBMapper.delete(request);
        return request;
    }

    public QueryResultPage<Request> getRequestsByReceiverId(String receiverId, int limit, Map<String, AttributeValue> startKey) {
        Map<String, AttributeValue> values = new HashMap<>();
        values.put(":receiverId", new AttributeValue().withS(receiverId));

        DynamoDBQueryExpression<Request> query = new DynamoDBQueryExpression<Request>()
                .withIndexName("receiverIdIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("receiverId = :receiverId")
                .withExpressionAttributeValues(values)
                .withLimit(limit);

        if (startKey != null) {
            query.setExclusiveStartKey(startKey);
        }

        return dynamoDBMapper.queryPage(Request.class, query);
    }



    public Request getreceivedRequest(String senderId){
        return dynamoDBMapper.load(Request.class,senderId,authUtil.getCurrentUser());
    }

    public Request getSentRequest(String receiverId){
        return dynamoDBMapper.load(Request.class, authUtil.getCurrentUser(),receiverId);
    }


}
