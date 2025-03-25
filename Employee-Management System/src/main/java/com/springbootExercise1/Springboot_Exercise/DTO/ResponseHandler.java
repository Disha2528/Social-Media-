package com.springbootExercise1.Springboot_Exercise.DTO;

public class ResponseHandler {
    private Object data;
    private String message;
    private int status;
    private boolean success;
    private String entity;

    public ResponseHandler(Object data, String message, int status, boolean success, String entity) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.success = success;
        this.entity = entity;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }
}
