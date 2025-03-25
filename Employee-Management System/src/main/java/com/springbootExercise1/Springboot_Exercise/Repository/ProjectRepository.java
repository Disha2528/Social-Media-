package com.springbootExercise1.Springboot_Exercise.Repository;

import com.springbootExercise1.Springboot_Exercise.Entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    public Project findByprojectId(String ProjectId);

   public  List<Project> findAllByProjectIdIn(List<String> projects);
}
