package com.coursemanagement.CourseManagement.DTO;

import com.coursemanagement.CourseManagement.Static.Role;
import com.coursemanagement.CourseManagement.group.OnCreate;
import com.coursemanagement.CourseManagement.group.OnLogin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    @NotBlank( message = "{user.id.invalid}")
    private String userId;

    @NotBlank(groups = OnCreate.class, message = "{user.name.invalid}")
    private String name;

    @NotBlank(groups = {OnCreate.class, OnLogin.class}, message = "{user.email.invalid}")
    @Email
    private String email;

    @NotBlank(groups = {OnCreate.class, OnLogin.class}, message = "{user.password.invalid}")
    private String password;

    @NotNull(groups = OnCreate.class, message = "{user.role.invalid}")
    private Role role;
}
