package com.SocialMedia.SocialMedia.Entities;

import com.SocialMedia.SocialMedia.Static.Gender;
import com.SocialMedia.SocialMedia.Util.GenderConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "User")
public class User {

    @DynamoDBHashKey(attributeName = "userName") // partition Key
    private String userName;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "emailIndex", attributeName = "email") //GSI
    private String email;

    @DynamoDBAttribute(attributeName = "password")
    private String password;

    @DynamoDBAttribute(attributeName = "bio")
    private String bio;

    @DynamoDBAttribute(attributeName = "profilePic")
    private String pfpPath;

    @DynamoDBAttribute(attributeName = "picName")
    private String pfpName;

    @DynamoDBAttribute(attributeName = "age")
    private Integer age;

    @DynamoDBAttribute(attributeName = "gender")
    @DynamoDBTypeConverted(converter = GenderConverter.class)
    private Gender gender;

    @DynamoDBAttribute(attributeName = "posts")
    private Integer postCount=0;

    @DynamoDBAttribute(attributeName = "friends")
    private Integer friendCount=0;


}
