package com.coursemanagement.CourseManagement.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.QueryResultPage;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.coursemanagement.CourseManagement.DTO.AnswerDTO;
import com.coursemanagement.CourseManagement.DTO.PaginatedResult;
import com.coursemanagement.CourseManagement.Entity.Answer;
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
        Answer answer = modelMapper.map(answerDTO, Answer.class);
        dynamoDBMapper.save(answer);
        return answer;
    }

    public Answer getAnswerById(String answerId){
        return dynamoDBMapper.load(Answer.class,answerId);
    }

    //retrieve
    public PaginatedResult<Answer> getAnswer(String discussionId, int limit, String lastEvaluatedAnswerId) {

        Map<String, AttributeValue> items = new HashMap<>();
        items.put(":discussionId", new AttributeValue().withS(discussionId));

        //query
        DynamoDBQueryExpression<Answer> queryExpression = new DynamoDBQueryExpression<Answer>()
                .withIndexName("discussionIdIndex")
                .withConsistentRead(false)
                .withKeyConditionExpression("discussionId = :discussionId")
                .withExpressionAttributeValues(items)
                .withLimit(limit);

        //assign lastEvaluatedKey as exclusiveStartKey
        if (lastEvaluatedAnswerId != null) {
            Map<String, AttributeValue> startKey = new HashMap<>();
            startKey.put("discussionId", new AttributeValue().withS(discussionId));
            startKey.put("answerId", new AttributeValue().withS(lastEvaluatedAnswerId));
            queryExpression.setExclusiveStartKey(startKey);
        }

        //querying
        QueryResultPage<Answer> queryResultPage = dynamoDBMapper.queryPage(Answer.class, queryExpression);

        List<Answer> answers = queryResultPage.getResults();
        Map<String, AttributeValue> lastKeyMap = queryResultPage.getLastEvaluatedKey();

        //extract next lastEvaluatedKey from last item returned in response
        String nextLastEvaluatedAnswerId = null;
        if (lastKeyMap != null && lastKeyMap.containsKey("answerId")) {
            nextLastEvaluatedAnswerId = lastKeyMap.get("answerId").getS();
        }

        return new PaginatedResult<>(answers, nextLastEvaluatedAnswerId);
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
