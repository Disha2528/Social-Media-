package com.coursemanagement.CourseManagement.DTO;

import com.coursemanagement.CourseManagement.group.OnCreate;
import com.coursemanagement.CourseManagement.group.OnDelete;
import com.coursemanagement.CourseManagement.group.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {

    @NotBlank(groups={OnUpdate.class, OnDelete.class}, message = "{answer.id.invalid}")
    private String answerId;

    @NotBlank(groups={OnCreate.class}, message = "{discussion.id.invalid}")
    private String discussionId;

    private String responderId;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class}, message = "{answer.text.invalid}")
    private String answerText;

    private LocalDateTime answeredAt=LocalDateTime.now();




}
