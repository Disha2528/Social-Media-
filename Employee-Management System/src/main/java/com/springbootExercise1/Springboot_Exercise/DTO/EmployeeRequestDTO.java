package com.springbootExercise1.Springboot_Exercise.DTO;

public class EmployeeRequestDTO {

    private String deptId;
    private int pageNumber = 0;
    private int pageSize = 10;
    private String sortBy = "name";
    private String sortDirection = "asc";


    public EmployeeRequestDTO() {
    }

    public EmployeeRequestDTO(String deptId, int pageNumber, int pageSize, String sortBy, String sortDirection) {
        this.deptId = deptId;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.sortBy = sortBy;
        this.sortDirection = sortDirection;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }
}
