package com.SocialMedia.SocialMedia.Entities;

import com.SocialMedia.SocialMedia.Util.LocalDateTimeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Friend")
public class Friend {

    @DynamoDBHashKey(attributeName = "userName")
    private String userName;

    @DynamoDBRangeKey(attributeName = "friendName")
    private String friendName;

    @DynamoDBAttribute(attributeName = "friendsSince")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    private LocalDateTime friendshipDate;
}
