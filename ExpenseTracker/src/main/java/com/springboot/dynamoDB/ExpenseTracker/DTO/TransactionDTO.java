package com.springboot.dynamoDB.ExpenseTracker.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {


    private String transactionId;

    private String userId;

    @Positive(message = "transaction.amount.valid")
    private double amount;

    @NotBlank
    @Pattern(regexp = "income|expense", message = "{transaction.type.valid}")
    private String type;

    @PastOrPresent(message = "{transaction.date.valid}")
    private LocalDateTime date;

    @NotBlank(message = "{transaction.description.valid}")
    private String Description;

    @NotBlank(message = "{transaction.category.valid}")
    private String category;
}
