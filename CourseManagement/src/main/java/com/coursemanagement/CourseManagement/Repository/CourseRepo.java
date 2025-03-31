package com.coursemanagement.CourseManagement.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.coursemanagement.CourseManagement.Entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;


    //create course
    public Course addCourse(Course course){
       dynamoDBMapper.save(course);
       return course;
    }

    //retrieve course
    public List<Course> getCourses(){
        List<Course> course= dynamoDBMapper.scan(Course.class, new DynamoDBScanExpression());
        return course;
    }

    //retrieve course by instructor
    public List<Course> courseByInstructor(String instructorId) {

        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":v_instructor", new AttributeValue().withS(instructorId));

        DynamoDBQueryExpression<Course> queryExpression = new DynamoDBQueryExpression<Course>()
                .withIndexName("instructorIdIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("instructorId = :v_instructor")
                .withExpressionAttributeValues(expressionValues);

        return dynamoDBMapper.query(Course.class, queryExpression);
    }

    //retrieve course by id
    public Course getCourseById(String id){
        Course course= dynamoDBMapper.load(Course.class,id);
        return course;
    }

    //delete
    public Course delete(String id){
        Course course = dynamoDBMapper.load(Course.class,id );
        dynamoDBMapper.delete(course);
        return course;
    }

}
