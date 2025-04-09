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
@DynamoDBTable(tableName = "Like")
public class Like {

    @DynamoDBRangeKey(attributeName = "likerId")
    @DynamoDBIndexHashKey(globalSecondaryIndexName = "likerIdIndex", attributeName = "likerId")
    private String likerId;

    @DynamoDBHashKey(attributeName = "postId")
    private String postId;

    @DynamoDBAttribute(attributeName = "likedateTime")
    @DynamoDBTypeConverted(converter = LocalDateTimeConverter.class)
    public LocalDateTime likeDateTime= LocalDateTime.now();


}
