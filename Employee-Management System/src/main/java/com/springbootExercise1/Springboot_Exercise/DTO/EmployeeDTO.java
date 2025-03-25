package com.springbootExercise1.Springboot_Exercise.DTO;

import com.springbootExercise1.Springboot_Exercise.Entity.Department;
import com.springbootExercise1.Springboot_Exercise.Entity.Project;

import java.util.List;

public class EmployeeDTO {


    private int id;
    private String name;
    private Department dept;
    private List<String> projects;
    private double salary;
    private String role;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int id, String name, Department dept, List<String> projects, double salary, String role) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.projects = projects;
        this.salary = salary;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
