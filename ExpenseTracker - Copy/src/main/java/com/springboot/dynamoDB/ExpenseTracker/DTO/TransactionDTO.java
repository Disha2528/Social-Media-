package com.springboot.dynamoDB.ExpenseTracker.DTO;

import com.springboot.dynamoDB.ExpenseTracker.Util.LocalDateTimeConverter;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private double amount;

    @NotBlank(message = "Type cannot be blank")
    private String type;

    @NotBlank(message = "Description cannot be blank")
    private String Description;

    @NotBlank(message = "Category cannot be blank")
    private String category;
}
