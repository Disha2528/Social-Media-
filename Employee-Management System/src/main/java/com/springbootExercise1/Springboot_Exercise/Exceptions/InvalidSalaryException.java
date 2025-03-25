package com.springbootExercise1.Springboot_Exercise.Exceptions;

public class InvalidSalaryException extends Exception{

    public InvalidSalaryException(String message) {
        super(message);
    }
}
