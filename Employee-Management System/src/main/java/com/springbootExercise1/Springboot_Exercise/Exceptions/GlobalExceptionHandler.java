package com.springbootExercise1.Springboot_Exercise.Exceptions;

import com.springbootExercise1.Springboot_Exercise.DTO.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {


    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleEmployeeNotFound(EmployeeNotFoundException e){
        ErrorDTO errorDTO= new ErrorDTO(e.getMessage(),HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidSalaryException.class)
    public ResponseEntity<ErrorDTO> handleInvalidSalaryException(InvalidSalaryException e){
        ErrorDTO errorDTO= new ErrorDTO(e.getMessage(),HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception ex){
        ErrorDTO errorDTO= new ErrorDTO("Error occured", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(errorDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
