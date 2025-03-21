package com.springboot.dynamoDB.ExpenseTracker.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.springboot.dynamoDB.ExpenseTracker.DTO.CategoryDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.TransactionRequestDTO;
import com.springboot.dynamoDB.ExpenseTracker.Entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    //create transaction
    public Transaction addTransaction(Transaction transaction){
        dynamoDBMapper.save(transaction);
        return transaction;
    }

    //retrieve all
    public List<Transaction> getTransactions(){
        return dynamoDBMapper.scan(Transaction.class, new DynamoDBScanExpression());
    }

    //Retrieve by user Id
    public List<Transaction> getTransactionByUserId(String userId) {
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("userId = :userId")
                .withExpressionAttributeValues(Map.of(":userId", new AttributeValue().withS(userId)));

        return dynamoDBMapper.scan(Transaction.class, scanExpression);
    }

    //retrieve by Transaction id
    public Transaction getTransactionById(String transactionId){
        Transaction transaction= dynamoDBMapper.load(Transaction.class,transactionId);
        return transaction;
    }


    //update in service
    //delete
    public void delete(Transaction transaction){
       dynamoDBMapper.delete(transaction);
    }

    //filter by date
    public List<Transaction> getTransactionsByDate(TransactionRequestDTO dto) {

        LocalDate date = dto.getDate(); // assuming it's LocalDate
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.plusDays(1).atStartOfDay().minusNanos(1);

        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":uid", new AttributeValue().withS(dto.getUserId()));
        eav.put(":start", new AttributeValue().withS(startOfDay.toString()));
        eav.put(":end", new AttributeValue().withS(endOfDay.toString()));

        Map<String, String> ean = new HashMap<>();
        ean.put("#dt", "date");

        DynamoDBQueryExpression<Transaction> queryExpression = new DynamoDBQueryExpression<Transaction>()
                .withIndexName("userId-date-index")
                .withConsistentRead(false)
                .withKeyConditionExpression("userId = :uid AND #dt BETWEEN :start AND :end")
                .withExpressionAttributeValues(eav)
                .withExpressionAttributeNames(ean);

        List<Transaction> results = dynamoDBMapper.query(Transaction.class, queryExpression);

        return dynamoDBMapper.query(Transaction.class, queryExpression);
    }




    //filter by category
    public List<Transaction> getTransactionsByCategory(CategoryDTO categoryDTO) {
        Map<String, AttributeValue> expressionValues = new HashMap<>();
        expressionValues.put(":userId", new AttributeValue().withS(categoryDTO.getUserId()));
        expressionValues.put(":category", new AttributeValue().withS(categoryDTO.getCategory()));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("userId = :userId AND category = :category")
                .withExpressionAttributeValues(expressionValues);

        return dynamoDBMapper.scan(Transaction.class, scanExpression);

    }





}
