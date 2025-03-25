package com.springbootExercise1.Springboot_Exercise.Controller;

import com.springbootExercise1.Springboot_Exercise.DTO.SalaryDetailDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.SalaryDetail;
import com.springbootExercise1.Springboot_Exercise.Exceptions.EmployeeNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Exceptions.InvalidSalaryException;
import com.springbootExercise1.Springboot_Exercise.Service.SalaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/SalaryDetail")
public class SalaryDetailController {

    @Autowired
    SalaryDetailService salaryDetailService;

    @GetMapping("/getSalaryDetails")
    public List<SalaryDetail> getAllSalaries(){
        return salaryDetailService.getAllSalaries();
    }

    @PostMapping("/updateSalary")
    public SalaryDetail updateSalary(@RequestBody SalaryDetailDTO salaryDetailDTO) throws EmployeeNotFoundException, InvalidSalaryException {
        return salaryDetailService.updateSalary(salaryDetailDTO);

    }
}
