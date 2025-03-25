package com.springbootExercise1.Springboot_Exercise.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springbootExercise1.Springboot_Exercise.Exceptions.InvalidSalaryException;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employee_details")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private int empId;

    @Column(name = "emp_name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToMany(mappedBy = "employees")
    @Nonnull
    @JsonIgnore
    private List<Project> projects;

    @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL)
    private SalaryDetail salaryDetail;

    @Column(name = "salary")
    private double salary;


    private String role;

    public Employee() {
    }

    public Employee( String name, Department department, @Nonnull List<Project> projects, SalaryDetail salaryDetail, double salary, String role) {
        this.name = name;
        this.department = department;
        this.projects = projects;
        this.salaryDetail = salaryDetail;
        this.salary = salary;
        this.role = role;
    }

    public int getId() {
        return empId;
    }

    public void setId(int id) {
        this.empId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public SalaryDetail getSalaryDetail() {
        return salaryDetail;
    }

    public void setSalaryDetail(SalaryDetail salaryDetail) {
        this.salaryDetail = salaryDetail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSalary(double salary) throws InvalidSalaryException {
        this.salary = salary;

        try{
            if(salary<0) throw new InvalidSalaryException("Enter Valid Salary");
        } catch (InvalidSalaryException e) {
            System.out.println(e.getMessage());
        }


        if (this.salaryDetail == null) {
            this.salaryDetail = new SalaryDetail();
            this.salaryDetail.setEmployee(this);
        }

        double basicSalary = salary * 0.50;
        double hra = salary * 0.30;
        double deductions = salary * 0.10;
        double bonus = salary*0.10;
        double total = basicSalary + hra + bonus+deductions;
        double netSalary = total - deductions;

        this.salaryDetail.setBasicSalary(basicSalary);
        this.salaryDetail.setHra(hra);
        this.salaryDetail.setBonus(bonus);
        this.salaryDetail.setDeductions(deductions);
        this.salaryDetail.setNetSalary(netSalary);
        this.salaryDetail.setTotal(total);

        this.salaryDetail.setEmployee(this);
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public Department getDept() {
        return department;
    }
}
