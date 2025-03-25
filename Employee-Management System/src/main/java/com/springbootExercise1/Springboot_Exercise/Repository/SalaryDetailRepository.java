package com.springbootExercise1.Springboot_Exercise.Repository;

import com.springbootExercise1.Springboot_Exercise.Entity.Project;
import com.springbootExercise1.Springboot_Exercise.Entity.SalaryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaryDetailRepository extends JpaRepository<SalaryDetail, Long> {
    public SalaryDetail findByEmployeeId(int id);
}
