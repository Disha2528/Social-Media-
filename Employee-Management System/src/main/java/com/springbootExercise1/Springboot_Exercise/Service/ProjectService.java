package com.springbootExercise1.Springboot_Exercise.Service;

import com.springbootExercise1.Springboot_Exercise.DTO.ProjectDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {


    public Project saveProject(ProjectDTO projectDTO);

    public List<Project> getAllProjects();

   public  Optional<Project> getProjectById(String id);

    Project modifyProject(ProjectDTO projectDTO);

    <Project> Optional<Project> deleteProject(String id);
}
