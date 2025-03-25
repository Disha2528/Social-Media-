package com.springbootExercise1.Springboot_Exercise.Exceptions;

public class DepartmentNotFoundException extends Exception{
    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
