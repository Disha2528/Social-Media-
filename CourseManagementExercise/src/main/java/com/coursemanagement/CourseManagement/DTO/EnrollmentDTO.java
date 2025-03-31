package com.coursemanagement.CourseManagement.DTO;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import com.coursemanagement.CourseManagement.group.OnCreate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EnrollmentDTO {

    @NotBlank(groups= OnCreate.class, message = "{course.id.invalid}")
    private String courseId;

    @NotBlank(groups = OnCreate.class, message="{student.id.invalid}")
    private String studentId;
}
