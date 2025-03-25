package com.springbootExercise1.Springboot_Exercise.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Project {

    @Id
    @Column(name = "Project-Id",nullable = false)
    private String projectId;

    @Column(name = "Project-Name", nullable = false)
    private String projectName;

    @Column(name = "Start-Date", nullable = false)
    private LocalDate startDate;

    @Column(name = "End-Date",nullable = false)
    private LocalDate endDate;

    @Column(name = "Progress",nullable = false)
    private float progress;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(
            name = "project_employees",
            joinColumns = @JoinColumn(name = "projects_project_id"),
            inverseJoinColumns = @JoinColumn(name = "employees_emp_id"))
    private List<Employee> employees;

    public Project() {
    }

    public Project(String projectId, String projectName, LocalDate startDate, LocalDate endDate, float progress, List<Employee> emp) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        employees = emp;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public List<Employee> getEmp() {
        return employees;
    }

    public void setEmp(List<Employee> emp) {
        employees = emp;
    }
}
