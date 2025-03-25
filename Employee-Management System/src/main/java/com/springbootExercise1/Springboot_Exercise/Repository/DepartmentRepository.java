package com.springbootExercise1.Springboot_Exercise.Repository;

import com.springbootExercise1.Springboot_Exercise.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, String> {


}
