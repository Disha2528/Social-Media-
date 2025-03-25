package com.springbootExercise1.Springboot_Exercise.Controller;

import com.springbootExercise1.Springboot_Exercise.DTO.ProjectDTO;
import com.springbootExercise1.Springboot_Exercise.DTO.ResponseHandler;
import com.springbootExercise1.Springboot_Exercise.Entity.Project;
import com.springbootExercise1.Springboot_Exercise.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;


    @GetMapping("/all")
    public ResponseEntity<ResponseHandler> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        ResponseHandler response = new ResponseHandler(
                projects,
                "Projects Retrieved Successfully",
                HttpStatus.OK.value(),
                true,
                "projects"
        );
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ResponseHandler> getProjectById(@PathVariable String id) {
        Optional<Project> project = projectService.getProjectById(id);
        ResponseHandler response = new ResponseHandler(
                project.orElse(null),
                project.isPresent() ? "Project Found" : "Project Not Found",
                project.isPresent() ? HttpStatus.OK.value() : HttpStatus.NOT_FOUND.value(),
                project.isPresent(),
                "project"
        );
        return ResponseEntity.status(project.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND).body(response);
    }


    @PostMapping("/add")
    public ResponseEntity<ResponseHandler> createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = projectService.saveProject(projectDTO);
        ResponseHandler response = new ResponseHandler(
                project,
                "Project Created Successfully",
                HttpStatus.CREATED.value(),
                true,
                "project"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/update")
    public ResponseEntity<ResponseHandler> modifyProject(@RequestBody ProjectDTO projectDTO) {
        Project updatedProject = projectService.modifyProject(projectDTO);
        ResponseHandler response = new ResponseHandler(
                updatedProject,
                "Project Updated Successfully",
                HttpStatus.OK.value(),
                true,
                "project"
        );
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseHandler> deleteProject(@PathVariable String id) {
        Optional<Project> deletedProject = projectService.getProjectById(id);


        ResponseHandler successResponse = new ResponseHandler(
                deletedProject.get(),
                "Project Deleted Successfully",
                HttpStatus.OK.value(),
                true,
                "project"
        );
        projectService.deleteProject(id);
        return ResponseEntity.ok(successResponse);
    }
}
