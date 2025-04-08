package com.SocialMedia.SocialMedia.DTO;

import com.SocialMedia.SocialMedia.Entities.Comment;
import com.SocialMedia.SocialMedia.Groups.OnCreate;
import com.SocialMedia.SocialMedia.Groups.OnUpdate;
import com.SocialMedia.SocialMedia.Util.LocalDateTimeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    @NotNull(groups = {OnUpdate.class}, message = "commentId cannot be blank")
    private String commentId;

    private String commentorId;

    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "Comment Text cannot be blank")
    private String commentText;

    @NotNull(groups = {OnCreate.class, OnUpdate.class}, message = "PostId cannot be blank")
    private String postId;

    private LocalDateTime commentDateTime= LocalDateTime.now();


}
