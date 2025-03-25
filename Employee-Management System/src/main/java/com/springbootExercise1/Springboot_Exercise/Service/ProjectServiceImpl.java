package com.springbootExercise1.Springboot_Exercise.Service;

import com.springbootExercise1.Springboot_Exercise.DTO.ProjectDTO;
import com.springbootExercise1.Springboot_Exercise.Entity.Project;
import com.springbootExercise1.Springboot_Exercise.Repository.EmployeeRepository;
import com.springbootExercise1.Springboot_Exercise.Repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepo;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    @Override
    public Project saveProject(ProjectDTO projectDTO) {
        Project project= new Project();
        project.setProjectName(projectDTO.getProjectName());
        project.setProjectId(projectDTO.getId());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setProgress(projectDTO.getProgress());
        projectRepo.save(project);
        return project;
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    @Override
    public Optional<Project> getProjectById(String id){
        return Optional.ofNullable(projectRepo.findByprojectId(id));
    }

    @Override
    @Transactional
    public Project modifyProject(ProjectDTO projectDTO){
        Project project= projectRepo.findByprojectId(projectDTO.getId());
        project.setStartDate(projectDTO.getStartDate());
        project.setProgress(projectDTO.getProgress());
        project.setEndDate(projectDTO.getEndDate());
        project.setEmp(employeeRepository.findAllById(projectDTO.getEmployeeIds()));
        return projectRepo.save(project);
    }


    @Transactional
    @Override
    public <Project> Optional<Project> deleteProject(String id) {
        projectRepo.delete(projectRepo.findByprojectId(id));
        return null;
    }


}
