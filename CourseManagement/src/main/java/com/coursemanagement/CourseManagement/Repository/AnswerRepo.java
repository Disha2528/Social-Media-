package com.coursemanagement.CourseManagement.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.coursemanagement.CourseManagement.DTO.AnswerDTO;
import com.coursemanagement.CourseManagement.Entity.Answer;
import com.coursemanagement.CourseManagement.Entity.Discussion;
import com.coursemanagement.CourseManagement.Exception.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AnswerRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private ModelMapper modelMapper;


    //create
    public Answer createAnswer(AnswerDTO answerDTO){
        return modelMapper.map(answerDTO, Answer.class);
    }

    public Answer getAnswerById(String answerId){
        return dynamoDBMapper.load(Answer.class,answerId);
    }

    //retrieve
    public List<Answer> getAnswer(String discussionId) {

        Discussion discussion = dynamoDBMapper.load(Discussion.class,discussionId);

        Map<String, AttributeValue> eav= new HashMap<>();
        eav.put("discussionId", new AttributeValue().withS(discussionId));

        DynamoDBQueryExpression<Answer> queryExpression= new DynamoDBQueryExpression<Answer>()
                .withIndexName("discussionIdIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("discussionId= :discussionId")
                .withExpressionAttributeValues(eav);
        List<Answer> result= dynamoDBMapper.query(Answer.class, queryExpression);


        return result;
    }


    //update
    public Answer updateAnswer(AnswerDTO answerDTO)  {

        Answer answer= dynamoDBMapper.load(Answer.class, answerDTO.getAnswerId());

        modelMapper.map(answerDTO,answer);
        dynamoDBMapper.save(answer);

        return answer;
    }

    //Delete
    public Answer deleteAnswer(String answerId) {

        Answer answer= dynamoDBMapper.load(Answer.class, answerId);

        dynamoDBMapper.delete(answer);

        return answer;
    }

}
