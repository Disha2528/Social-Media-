package com.SocialMedia.SocialMedia.DTO;

import com.SocialMedia.SocialMedia.Groups.OnCreate;
import com.SocialMedia.SocialMedia.Static.Status;
import com.SocialMedia.SocialMedia.Util.StatusConverter;
import com.amazonaws.services.dynamodbv2.datamodeling.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

    private String senderId;

    @NotBlank(groups = {OnCreate.class}, message = "Enter the receiver Id")
    private String receiverId;

    private Status status;

    private LocalDateTime sentTime= LocalDateTime.now();
}
