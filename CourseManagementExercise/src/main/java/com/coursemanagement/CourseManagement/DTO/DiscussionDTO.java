package com.coursemanagement.CourseManagement.DTO;

import com.coursemanagement.CourseManagement.group.OnCreate;
import com.coursemanagement.CourseManagement.group.OnDelete;
import com.coursemanagement.CourseManagement.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscussionDTO {

    @NotBlank(groups= {OnDelete.class, OnUpdate.class}, message = "{discussion.id.invalid}")
    private String discussionId;

    @NotBlank(groups = {OnCreate.class}, message = "{course.id.invalid}")
    private String courseId;

    @NotBlank(groups={OnCreate.class}, message = "{discussion.question.invalid}")
    private String question;

    private LocalDateTime date= LocalDateTime.now();
}
