package com.springbootExercise1.Springboot_Exercise.DTO;

public class ErrorDTO {

    private String message;
    private int statusCode;
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
