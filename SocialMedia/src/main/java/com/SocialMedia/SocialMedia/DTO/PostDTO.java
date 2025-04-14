package com.SocialMedia.SocialMedia.DTO;

import com.SocialMedia.SocialMedia.Groups.OnCreate;
import com.SocialMedia.SocialMedia.Groups.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PostDTO {

    @NotBlank(groups={OnUpdate.class}, message = "Enter the PostId for post to be updated")
    private String postId;

    private String userName;

   @NotBlank(groups = {OnCreate.class}, message = "Post Path needs to be entered")
    private String postPath;

   @NotBlank(groups = {OnCreate.class,OnCreate.class}, message = "post name is required")
   private String postName;

   @NotBlank(groups = {OnUpdate.class}, message = "Caption cannot be blank")
    private String postCaption;

    private LocalDateTime postDate= LocalDateTime.now();
}
