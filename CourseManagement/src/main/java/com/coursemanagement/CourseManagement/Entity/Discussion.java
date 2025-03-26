package com.coursemanagement.CourseManagement.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.coursemanagement.CourseManagement.Util.LocalDateConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Discussion")
public class Discussion {

    @DynamoDBAttribute(attributeName = "discussionId")
    private String discussionId;

    @DynamoDBHashKey(attributeName = "studentId")
    private String studentId; // partition key

    @DynamoDBAttribute(attributeName = "question")
    private String question;

    @DynamoDBAttribute(attributeName = "instructorId")
    private String instructorId;

    @DynamoDBAttribute(attributeName = "answer")
    private String answer;

    @DynamoDBIndexHashKey(globalSecondaryIndexName ="date")
    @DynamoDBTypeConverted(converter = LocalDateConverter.class)
    private LocalDate date; // GSI
}
