package com.springboot.dynamoDB.ExpenseTracker.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {

    private String id;

    @NotBlank(message ="user.name.valid")
    private String name;

    @Email(message = "user.email.valid")
    private String email;

    @Size(min = 8, max=16, message="user.password.valid")
    private String password;

    @Positive(message = "user.balance.valid")
    private double balance;

}
