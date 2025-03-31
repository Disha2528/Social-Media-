package com.coursemanagement.CourseManagement.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    @NotBlank(message = "{error.message.invalid}")
    private String message;

    @NotNull(message = "{error.statusCode.invalid}")
    private int statusCode;

    @NotNull(message = "{error.timestamp.invalid}")
    private long timeStamp;

    public ErrorDTO(String message, int statusCode){
        this.message=message;
        this.statusCode=statusCode;
        this.timeStamp=System.currentTimeMillis();
    }

    public String getMessage(){
        return message;
    }

    public int getStatusCode(){
        return statusCode;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
