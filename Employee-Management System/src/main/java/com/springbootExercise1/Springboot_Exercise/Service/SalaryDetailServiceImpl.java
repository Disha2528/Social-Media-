package com.springbootExercise1.Springboot_Exercise.Service;

import com.springbootExercise1.Springboot_Exercise.DTO.SalaryDetailDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.Employee;
import com.springbootExercise1.Springboot_Exercise.Entity.SalaryDetail;
import com.springbootExercise1.Springboot_Exercise.Exceptions.EmployeeNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Exceptions.InvalidSalaryException;
import com.springbootExercise1.Springboot_Exercise.Repository.EmployeeRepository;
import com.springbootExercise1.Springboot_Exercise.Repository.SalaryDetailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service


public class SalaryDetailServiceImpl implements SalaryDetailService{

    @Autowired
    private SalaryDetailRepository salaryRepo;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<SalaryDetail> getAllSalaries(){
        return salaryRepo.findAll();
    }

    @Override
    public SalaryDetail findByEmployeeId(int EmpId){
        return salaryRepo.findByEmployeeId(EmpId);
    }

    @Transactional
    @Override
    public SalaryDetail updateSalary(SalaryDetailDTO salaryDetailDTO) throws EmployeeNotFoundException, InvalidSalaryException {
        Employee employee = employeeRepository.findById(salaryDetailDTO.getEmpId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with ID: " + salaryDetailDTO.getEmpId()));

        SalaryDetail salaryDetail = employee.getSalaryDetail();
        if (salaryDetail == null) {
            throw new RuntimeException("Salary details not found for employee ID: " + salaryDetailDTO.getEmpId());
        }

        // Update SalaryDetail fields
        salaryDetail.setBasicSalary(salaryDetailDTO.getBasicSalary());
        salaryDetail.setHra(salaryDetailDTO.getHra());
        salaryDetail.setBonus(salaryDetailDTO.getBonus());
        salaryDetail.setDeductions(salaryDetailDTO.getDeductions());

        // Calculate new total salary
        double totalSalary = salaryDetailDTO.getBasicSalary() + salaryDetailDTO.getHra() + salaryDetailDTO.getBonus()+ salaryDetail.getDeductions();
        double netSalary = totalSalary - salaryDetailDTO.getDeductions();

        salaryDetail.setTotal(totalSalary);
        salaryDetail.setNetSalary(netSalary);

        // Update Employee salary
        employee.setSalary(totalSalary);
        employeeRepository.save(employee);

        return salaryRepo.save(salaryDetail);
    }
}
