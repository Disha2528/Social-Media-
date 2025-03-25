package com.springbootExercise1.Springboot_Exercise.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.springbootExercise1.Springboot_Exercise.Entity.Employee;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee , Integer> {


   public List<Employee> findBySalaryBetween(double minSalary, double maxSalary);

    public Page<Employee> findByDepartment_DeptId(String deptId, Pageable pageable);

}
