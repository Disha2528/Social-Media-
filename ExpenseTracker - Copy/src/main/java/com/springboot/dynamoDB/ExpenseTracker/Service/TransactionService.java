package com.springboot.dynamoDB.ExpenseTracker.Service;

import com.springboot.dynamoDB.ExpenseTracker.DTO.CategoryDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.TransactionDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.TransactionRequestDTO;
import com.springboot.dynamoDB.ExpenseTracker.Entity.Transaction;
import com.springboot.dynamoDB.ExpenseTracker.Exceptions.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface TransactionService {
    //create Transaction
    public Transaction addTransaction(Transaction transaction) ;

    //Retrieve all
    public List<Transaction> getTransaction() throws EntityNotFoundException;

    //retrieve by user Id
    public List<Transaction> getTransactionByUserId(String userId) throws EntityNotFoundException;

    //retrieve by date
    public List<Transaction> getTransactionByDate(TransactionRequestDTO dto)throws EntityNotFoundException;

    //retrieve by category
   public  List<Transaction> getTransactionByCategory(CategoryDTO category)throws EntityNotFoundException;

    //update
    public Transaction updateTransaction (String id, TransactionDTO transactionDTO)throws EntityNotFoundException;

    //delete
    public Transaction deleteTransaction(String id) throws EntityNotFoundException;
}
