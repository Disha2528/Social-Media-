package com.springbootExercise1.Springboot_Exercise.Service;

import com.springbootExercise1.Springboot_Exercise.DTO.DepartmentDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.Department;
import com.springbootExercise1.Springboot_Exercise.Exceptions.DepartmentNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Exceptions.EmployeeNotFoundException;

import java.util.List;
import java.util.Optional;

public interface DeptService {


    public Department saveDept(DepartmentDTO departmentDTO) throws EmployeeNotFoundException;

    public List<Department> getDept();

    public Optional<Department> getDeptById(String id);

    public Department updateDept(DepartmentDTO departmentDTO);

    public Optional<Department> deleteDept(String id) throws DepartmentNotFoundException;
}
