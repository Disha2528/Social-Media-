package com.springboot.dynamoDB.ExpenseTracker.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotBlank(message = "{user.id.valid}")
    private String userId;

    @NotBlank(message = "{user.password.valid}")
    private String password;
}
