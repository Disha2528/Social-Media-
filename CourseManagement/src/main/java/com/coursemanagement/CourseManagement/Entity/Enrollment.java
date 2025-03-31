package com.coursemanagement.CourseManagement.Entity;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "Enrollments")
public class Enrollment {


    @DynamoDBAttribute(attributeName = "courseId")
    private String courseId;

    @DynamoDBHashKey(attributeName = "studentId") //partition key
    private String studentId;
}
