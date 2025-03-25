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
    public Transaction addTransaction(Transaction transaction){

        User user = userRepo.getUserById(transaction.getUserId());

        if(user ==null) throw new EntityNotFoundException("Entity Not Found");

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
    public List<Transaction> getTransaction() throws EntityNotFoundException{
        List<Transaction> transactions= transationRepo.getTransactions();

        if(transactions.isEmpty()) throw new EntityNotFoundException("Entity Not Found");

        return transactions;
    }

    //retrieve by user Id
    @Override
    public List<Transaction> getTransactionByUserId(String userId) throws EntityNotFoundException{
        User user = userRepo.getUserById(userId);

        if(user ==null) throw new EntityNotFoundException("Entity Not Found");

        return transationRepo.getTransactionByUserId(userId);
    }

    //retrieve by date
    @Override
    public List<Transaction> getTransactionByDate(TransactionRequestDTO dto) throws EntityNotFoundException{

        List<Transaction> transactions= transationRepo.getTransactionsByDate(dto);

        if(transactions.isEmpty()) throw new EntityNotFoundException("Entity Not Found");

        return transactions;


    }

    //retrieve by category
    @Override
    public List<Transaction> getTransactionByCategory(CategoryDTO category) throws EntityNotFoundException{
        List<Transaction> transactions= transationRepo.getTransactionsByCategory(category);

        if(transactions.isEmpty()) throw new EntityNotFoundException("Entity Not Found");

        return transactions;
    }

    //update
    @Override
    public Transaction updateTransaction(String id, TransactionDTO transactionDTO) throws EntityNotFoundException{
        Transaction transaction= transationRepo.getTransactionById(id);

        if(transaction==null) throw new EntityNotFoundException("Entity Not Found");


        modelMapper.map(transactionDTO,transaction);
        dynamoDBMapper.save(transaction);

        return transaction;
    }

    //delete
    @Override
    public Transaction deleteTransaction(String id) throws EntityNotFoundException{
        Transaction transaction= transationRepo.getTransactionById(id);

        if(transaction==null) throw new EntityNotFoundException("Entity Not Found");

        transationRepo.delete(transaction);

        return transaction;
    }


}
