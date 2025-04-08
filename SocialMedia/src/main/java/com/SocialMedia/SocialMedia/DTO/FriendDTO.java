package com.SocialMedia.SocialMedia.DTO;

import com.SocialMedia.SocialMedia.Groups.OnCreate;
import com.SocialMedia.SocialMedia.Groups.OnDelete;
import com.SocialMedia.SocialMedia.Util.LocalDateTimeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class FriendDTO {

    @NotBlank(groups = {OnCreate.class}, message = "userName cannot be blank")
    private String userName;

    @NotBlank(groups= {OnCreate.class, OnDelete.class}, message = "friendName cannot be blank")
    private String friendName;

    private LocalDateTime friendshipDate= LocalDateTime.now();
}
