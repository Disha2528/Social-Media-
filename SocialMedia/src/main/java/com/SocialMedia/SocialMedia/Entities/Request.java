package com.SocialMedia.SocialMedia.Entities;

import com.SocialMedia.SocialMedia.Static.Status;
import com.SocialMedia.SocialMedia.Util.LocalDateTimeConverter;
import com.SocialMedia.SocialMedia.Util.StatusConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Request")
public class Request {

    @DynamoDBHashKey(attributeName = "senderId")
    private String senderId;

    @DynamoDBRangeKey(attributeName = "receiverId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "receiverIdIndex", attributeName = "receiverId")
    private String receiverId;

    @DynamoDBAttribute(attributeName = "status")
    @DynamoDBTypeConverted(converter = StatusConverter.class)
    private Status status;

    @DynamoDBAttribute( attributeName = "sentTime")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime sentTime;


}
