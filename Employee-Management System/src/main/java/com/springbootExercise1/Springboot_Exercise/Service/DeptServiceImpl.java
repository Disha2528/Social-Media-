package com.springbootExercise1.Springboot_Exercise.Service;

import com.springbootExercise1.Springboot_Exercise.DTO.DepartmentDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.Department;
import com.springbootExercise1.Springboot_Exercise.Exceptions.DepartmentNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Exceptions.EmployeeNotFoundException;
import com.springbootExercise1.Springboot_Exercise.Repository.DepartmentRepository;
import com.springbootExercise1.Springboot_Exercise.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeptServiceImpl implements DeptService
{

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Transactional
    @Override
    public Department saveDept(DepartmentDTO departmentDTO) throws EmployeeNotFoundException {
        Department department= new Department();
        department.setDeptId(departmentDTO.getDeptId());
        departmentRepository.save(department);
        return department;
    }

    @Override
    public List<Department> getDept() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> getDeptById(String id) {
        return departmentRepository.findById(String.valueOf(id));
    }

    @Transactional
    @Override
    public Department updateDept(DepartmentDTO departmentDTO) {
        Department department= departmentRepository.findById(departmentDTO.getDeptId()).orElseThrow();
        department.setDeptId(departmentDTO.getDeptId());

        department.setEmployees(employeeRepository.findAllById(departmentDTO.getEmployeeIds()));
        return department;
    }

    @Transactional
    public Optional<Department> deleteDept(String id) throws DepartmentNotFoundException {
        Optional<Department> department= departmentRepository.findById(id);
        if(department==null){
            throw new DepartmentNotFoundException("Enter Valid Department");
        }
        return department;
    }


}
