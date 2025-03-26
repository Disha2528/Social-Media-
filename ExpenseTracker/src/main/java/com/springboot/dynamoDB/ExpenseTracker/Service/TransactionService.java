package com.springboot.dynamoDB.ExpenseTracker.Service;

import com.springboot.dynamoDB.ExpenseTracker.DTO.CategoryDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.TransactionDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.TransactionRequestDTO;
import com.springboot.dynamoDB.ExpenseTracker.Entity.Transaction;
import com.springboot.dynamoDB.ExpenseTracker.Exceptions.EntityNotFoundException;

import java.security.Principal;
import java.util.List;

public interface TransactionService {


    //create Transaction
    public Transaction addTransaction(TransactionDTO transactionDTO, Principal principal);

    //Retrieve all
    public List<Transaction> getTransaction(Principal principal) throws EntityNotFoundException;

    //retrieve by user Id
    public List<Transaction> getTransactionByUserId(String userId) throws EntityNotFoundException;


    //retrieve by date
    public List<Transaction> getTransactionByDate(TransactionRequestDTO dto, Principal principal) throws EntityNotFoundException;

    //retrieve by category
    public List<Transaction> getTransactionByCategory(CategoryDTO category, Principal principal) throws EntityNotFoundException;

    //update
    public Transaction updateTransaction(String id, TransactionDTO transactionDTO, Principal principal) throws EntityNotFoundException;


    //delete
    public Transaction deleteTransaction(String id, Principal principal) throws EntityNotFoundException;
}
