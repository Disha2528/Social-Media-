package com.springbootExercise1.Springboot_Exercise.Exceptions;

public class EmployeeNotFoundException extends Exception{


    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
