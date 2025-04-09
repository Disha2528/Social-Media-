package com.SocialMedia.SocialMedia.DTO;

import com.SocialMedia.SocialMedia.Groups.OnCreate;
import com.SocialMedia.SocialMedia.Groups.OnDelete;
import com.SocialMedia.SocialMedia.Util.LocalDateTimeConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDTO {


    private String likerId;

    @NotBlank(groups = {OnCreate.class, OnDelete.class}, message = "Post Id cannot be blank")
    private String postId;

    public LocalDateTime likeDateTime= LocalDateTime.now();

}
