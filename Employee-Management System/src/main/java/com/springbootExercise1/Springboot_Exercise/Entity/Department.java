package com.springbootExercise1.Springboot_Exercise.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @Column(name = "dept_id",nullable = false)
    private String deptId;


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Employee> employees;


    public Department() {
    }

    public Department(String deptId) {
        this.deptId = deptId;

    }


    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


}
