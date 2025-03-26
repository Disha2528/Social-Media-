package com.springboot.dynamoDB.ExpenseTracker.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {

    private String UserId;

    @NotBlank(message = "{category.valid}")
    private String category;
}
