package com.coursemanagement.CourseManagement.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.coursemanagement.CourseManagement.DTO.EnrollmentDTO;
import com.coursemanagement.CourseManagement.Entity.Enrollment;
import com.coursemanagement.CourseManagement.Entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EnrollmentRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;
    //create
    public Enrollment addEnrollment(EnrollmentDTO enrollmentDTO){
        Enrollment enrollment =modelMapper.map(enrollmentDTO, Enrollment.class);
         dynamoDBMapper.save(enrollment);
         return enrollment;
    }

    //retrieve Enrollments of a particular student
    public List<Enrollment> enrolledCourses(String studentId) {
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":studentId", new AttributeValue().withS(studentId));

        DynamoDBQueryExpression<Enrollment> queryExpression = new DynamoDBQueryExpression<Enrollment>()
                .withKeyConditionExpression("studentId = :studentId")
                .withExpressionAttributeValues(expressionValues);

        return dynamoDBMapper.query(Enrollment.class, queryExpression);
    }

}
