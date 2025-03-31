package com.coursemanagement.CourseManagement.DTO;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.coursemanagement.CourseManagement.group.OnCreate;
import com.coursemanagement.CourseManagement.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "CourseId")
public class CourseDTO {

    @NotBlank(groups = OnUpdate.class)
    private String courseId;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "{course.title.invalid}")
    private String title;

    private String instructorId;


}
