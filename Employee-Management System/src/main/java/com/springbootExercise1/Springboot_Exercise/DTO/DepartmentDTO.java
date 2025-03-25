package com.springbootExercise1.Springboot_Exercise.DTO;

import java.util.List;

public class DepartmentDTO {
    private String deptId;
    private List<Integer> employeeIds;
    public DepartmentDTO() {
    }

    public DepartmentDTO(String deptId, List<Integer> employeeIds) {
        this.deptId = deptId;
        this.employeeIds = employeeIds;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<Integer> getEmployeeIds() {
        return employeeIds;
    }

    public void setEmployeeIds(List<Integer> employeeIds) {
        this.employeeIds = employeeIds;
    }
}
