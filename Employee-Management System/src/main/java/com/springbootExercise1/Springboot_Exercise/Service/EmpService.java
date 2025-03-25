package com.springbootExercise1.Springboot_Exercise.Service;

import com.springbootExercise1.Springboot_Exercise.DTO.EmployeeDTO;
import com.springbootExercise1.Springboot_Exercise.DTO.SalaryRangeDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.Employee;
import com.springbootExercise1.Springboot_Exercise.Exceptions.EmployeeNotFoundException;
import com.springbootExercise1.Springboot_Exercise.DTO.EmployeeRequestDTO;
import com.springbootExercise1.Springboot_Exercise.Exceptions.InvalidSalaryException;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmpService {

    public Employee saveEmp(EmployeeDTO employeeDTO) throws InvalidSalaryException, IllegalAccessException;

    public List<Employee> getAllEmployee();

    public Employee getEmployeeById(int id) throws EmployeeNotFoundException;

    public Employee modifyEmployee(Employee employeeDTO) throws EmployeeNotFoundException, InvalidSalaryException;

    public Employee deleteEmployee(int empId) throws EmployeeNotFoundException;

    public List<Employee> getEmployeesByDepartment(String department);

    public List<Employee> getEmployeesBySalaryRange(SalaryRangeDTO salaryRangeDTO);

    public Page<Employee> getEmployeesById(@RequestBody EmployeeRequestDTO requestDTO);
}
