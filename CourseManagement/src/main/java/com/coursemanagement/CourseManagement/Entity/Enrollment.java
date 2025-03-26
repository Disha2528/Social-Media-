package com.coursemanagement.CourseManagement.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Enrollments")
public class Enrollment {

    @DynamoDBAttribute(attributeName = "enrollmentId")
    private String enrollmentId;

    @DynamoDBHashKey(attributeName = "courseId")
    private String courseId; //partition key

    @DynamoDBIndexHashKey(globalSecondaryIndexName = "studentId")
    private String studentId;
}
