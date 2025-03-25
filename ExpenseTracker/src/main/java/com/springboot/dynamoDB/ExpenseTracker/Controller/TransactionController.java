package com.springboot.dynamoDB.ExpenseTracker.Controller;


import com.springboot.dynamoDB.ExpenseTracker.DTO.CategoryDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.TransactionDTO;
import com.springboot.dynamoDB.ExpenseTracker.DTO.TransactionRequestDTO;
import com.springboot.dynamoDB.ExpenseTracker.Entity.Transaction;
import com.springboot.dynamoDB.ExpenseTracker.Service.TransactionService;
import com.springboot.dynamoDB.ExpenseTracker.Service.TransactionServiceImpl;
import com.springboot.dynamoDB.ExpenseTracker.Util.ResponseHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MessageSource messageSource;

    @PostMapping("/add")
    public ResponseEntity<ResponseHandler> addTransaction(@RequestBody @Valid TransactionDTO transaction){

        Transaction transaction1= transactionService.addTransaction(transaction);

        ResponseHandler response = new ResponseHandler(transaction1, messageSource.getMessage("transaction.create.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "Transaction");


        return ResponseEntity.ok(response);
    }

    //retrieve all transactions
    @GetMapping("/transactions")
    public ResponseEntity<ResponseHandler> getTransactions(){

        List<Transaction> transactions=  transactionService.getTransaction();

        ResponseHandler response = new ResponseHandler(transactions, messageSource.getMessage("transactions.get.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "Transaction");

        return ResponseEntity.ok(response);

    }


    //retrieve by user Id
    @GetMapping("/transactions/{id}")
    public ResponseEntity<ResponseHandler> getTransactionByUserId(@PathVariable @Valid String id){

        List<Transaction> transactions=  transactionService.getTransactionByUserId(id);

        ResponseHandler response = new ResponseHandler(transactions, messageSource.getMessage("transactions.get.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "Transaction");

        return ResponseEntity.ok(response);

    }

    //filter by date
    @PostMapping("/byDate")
    public ResponseEntity<ResponseHandler> getTransactionByDate(@RequestBody @Valid TransactionRequestDTO dto){

        List<Transaction> transactions=  transactionService.getTransactionByDate(dto);

        ResponseHandler response = new ResponseHandler(transactions, messageSource.getMessage("transactions.get.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "Transaction");

        return ResponseEntity.ok(response);

    }

    //filter by category
    @PostMapping("/byCategory")
    public ResponseEntity<ResponseHandler> getTransactionByCategory( @RequestBody @Valid CategoryDTO category){

        List<Transaction> transactions=  transactionService.getTransactionByCategory(category);

        ResponseHandler response = new ResponseHandler(transactions, messageSource.getMessage("transactions.get.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "Transaction");

        return ResponseEntity.ok(response);
    }

    //update
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseHandler> updateTransaction(@PathVariable @Valid String id, @RequestBody @Valid TransactionDTO transactionDTO){
         Transaction transaction= transactionService.updateTransaction(id,transactionDTO);

        ResponseHandler response = new ResponseHandler(transaction, messageSource.getMessage("transactions.update.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "Transaction");

        return ResponseEntity.ok(response);

    }

    //delete
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseHandler> deleteTransaction(@PathVariable @Valid String id){

        Transaction transaction= transactionService.deleteTransaction(id);

        ResponseHandler response = new ResponseHandler(transaction, messageSource.getMessage("transaction.delete.success", null, LocaleContextHolder.getLocale()),
                HttpStatus.OK.value(), true, "Transaction");

        return ResponseEntity.ok(response);
    }



}
