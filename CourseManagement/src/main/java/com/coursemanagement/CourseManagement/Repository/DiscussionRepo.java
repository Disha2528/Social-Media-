package com.coursemanagement.CourseManagement.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.coursemanagement.CourseManagement.DTO.DiscussionDTO;
import com.coursemanagement.CourseManagement.Entity.Discussion;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DiscussionRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ModelMapper modelMapper;

    //create
    public Discussion createDiscussion(DiscussionDTO discussionDTO){
        Discussion discussion=modelMapper.map(discussionDTO, Discussion.class);
        dynamoDBMapper.save(discussion);
        return discussion;
    }

    //retrieve
    public List<Discussion> getDiscussions(){
        return dynamoDBMapper.scan(Discussion.class, new DynamoDBScanExpression());
    }

    //retrieve discussion on particular course
    public List<Discussion> getDiscussionsByCourseId(String courseId) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":courseId", new AttributeValue().withS(courseId));

        DynamoDBQueryExpression<Discussion> queryExpression = new DynamoDBQueryExpression<Discussion>()
                .withIndexName("courseIdIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("courseId = :courseId")
                .withExpressionAttributeValues(eav);

        return dynamoDBMapper.query(Discussion.class, queryExpression);
    }

}
