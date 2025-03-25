package com.springbootExercise1.Springboot_Exercise.Service;

import com.springbootExercise1.Springboot_Exercise.DTO.SalaryDetailDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.SalaryDetail;
import com.springbootExercise1.Springboot_Exercise.Exceptions.EmployeeNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Exceptions.InvalidSalaryException;
import jakarta.transaction.Transactional;

import java.util.List;

public interface SalaryDetailService {


    public List<SalaryDetail> getAllSalaries();

    public SalaryDetail findByEmployeeId(int EmpId);

   public SalaryDetail updateSalary(SalaryDetailDTO salaryDetailDTO) throws EmployeeNotFoundException, InvalidSalaryException;
}
