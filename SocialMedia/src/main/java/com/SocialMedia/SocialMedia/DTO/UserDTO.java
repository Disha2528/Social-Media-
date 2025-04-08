package com.SocialMedia.SocialMedia.DTO;

import com.SocialMedia.SocialMedia.Groups.*;
import com.SocialMedia.SocialMedia.Static.Gender;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank(groups = {OnCreate.class, OnLogin.class}, message = "Username is required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String userName;

    @NotBlank(groups =  {OnLogin.class, OnCreate.class, OnUpdatePassword.class}, message = "password cannot be blank")
    @Size(min = 8, max = 16, groups = OnLogin.class, message = "Password must be 8 to 16 Character long")
    private String password;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class, OnUpdateEmail.class}, message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Size( groups= {OnUpdate.class, OnUpdateBio.class}, max = 500, message = "Bio should not exceed 500 characters")
    private String bio;

    @NotNull(groups = {OnUpdate.class, OnUpdatePfp.class}, message = "Enter a profile pic")
    private String pfpPath;

    @NotNull(groups = OnCreate.class, message = "Age is required")
    @Min(value = 18, message = "Age must be 18 or above")
    private int age;

    @NotNull(groups = OnCreate.class, message = "Gender is required")
    private Gender gender;



}
