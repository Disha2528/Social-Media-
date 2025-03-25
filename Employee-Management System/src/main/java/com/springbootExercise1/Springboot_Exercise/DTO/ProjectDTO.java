package com.springbootExercise1.Springboot_Exercise.DTO;

import java.time.LocalDate;
import java.util.List;

public class ProjectDTO {
    private String  id;
    private String projectName;
    private List<Integer> employeeIds;
    private LocalDate startDate;
    private LocalDate endDate;
    private float progress;

    public ProjectDTO() {
    }

    public ProjectDTO(String id, String projectName, List<Integer> employeeIds, LocalDate startDate, LocalDate endDate, float progress) {
        this.id = id;
        this.projectName = projectName;
        this.employeeIds = employeeIds;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<Integer> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Integer> employeeIds) {
        this.employeeIds = employeeIds;
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
}
