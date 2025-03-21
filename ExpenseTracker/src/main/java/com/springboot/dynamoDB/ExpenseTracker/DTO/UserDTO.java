package com.springboot.dynamoDB.ExpenseTracker.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDTO {

    @NotBlank(message ="Name cannot be blank")
    private String name;

    @Email
    private String email;

    @Size(min = 8, max=16, message="Password must be 8 to 16 character long")
    private String password;

    private double balance;

}
