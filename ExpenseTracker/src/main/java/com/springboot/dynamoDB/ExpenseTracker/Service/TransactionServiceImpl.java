package com.springboot.dynamoDB.ExpenseTracker.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.springboot.dynamoDB.ExpenseTracker.DTO.CategoryDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.TransactionDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.TransactionRequestDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.UserDTO;
import com.springboot.dynamoDB.ExpenseTracker.Entity.Transaction;
import com.springboot.dynamoDB.ExpenseTracker.Entity.User;
import com.springboot.dynamoDB.ExpenseTracker.Exceptions.EntityNotFoundException;
import com.springboot.dynamoDB.ExpenseTracker.Repository.TransactionRepo;
import com.springboot.dynamoDB.ExpenseTracker.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepo transationRepo;

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;

    //create Transaction
    @Override
    public Transaction addTransaction(TransactionDTO transactionDTO, Principal principal){
        transactionDTO.setUserId(principal.getName());
       Transaction transaction=  modelMapper.map(transactionDTO, Transaction.class);

        User user= userRepo.getUserById(transaction.getUserId());

        if(user==null) throw new EntityNotFoundException("Entity Not Found");

        double currentBalance= user.getBalance();

        if (transaction.getType().equalsIgnoreCase("income")) {
            currentBalance += transaction.getAmount();
        } else {
            if (transaction.getAmount() > currentBalance) {
                throw new RuntimeException("Insufficient balance.");
            }
            currentBalance -= transaction.getAmount();
        }

        user.setBalance(currentBalance);
        UserDTO userDTO= modelMapper.map(user, UserDTO.class);
        userService.updateUser(user.getId(),userDTO);

        transationRepo.addTransaction(transaction);

        return transaction;
    }

    //Retrieve all
    @Override
    public List<Transaction> getTransaction(Principal principal) throws EntityNotFoundException{
        List<Transaction> transactions= transationRepo.getTransactions(principal.getName());

        return transactions;
    }


    //retrieve by user Id
    @Override
    public List<Transaction> getTransactionByUserId(String userId) throws EntityNotFoundException{
        User user= userRepo.getUserById(userId);

        if(user==null) throw new EntityNotFoundException("Entity Not Found");

        return transationRepo.getTransactionByUserId(userId);
    }

    //retrieve by date
    @Override
    public List<Transaction> getTransactionByDate(TransactionRequestDTO dto, Principal principal) throws EntityNotFoundException{
        dto.setUserId(principal.getName());
        List<Transaction> transactions= transationRepo.getTransactionsByDate(dto);
        return transactions;


    }

    //retrieve by category
    @Override
    public List<Transaction> getTransactionByCategory(CategoryDTO category, Principal principal) throws EntityNotFoundException{
        category.setUserId(principal.getName());
        List<Transaction> transactions= transationRepo.getTransactionsByCategory(category);
        return transactions;
    }

    //update
    @Override
    public Transaction updateTransaction(String id, TransactionDTO transactionDTO, Principal principal) throws EntityNotFoundException{
        Transaction transaction= transationRepo.getTransactionById(id);
        if (!transaction.getUserId().equals(principal.getName())) {
            throw new EntityNotFoundException("Entity Not Found");
        }

        if(transaction==null) throw new EntityNotFoundException("Entity Not Found");


        modelMapper.map(transactionDTO,transaction);
        dynamoDBMapper.save(transaction);

        return transaction;
    }

    //delete
    @Override
    public Transaction deleteTransaction(String id, Principal principal) throws EntityNotFoundException{

        Transaction transaction= transationRepo.getTransactionById(id);

        if(transaction==null || transaction.getUserId()!=principal.getName()) throw new EntityNotFoundException("Entity Not Found");

        transationRepo.delete(transaction);

        return transaction;
    }


}
