package com.springbootExercise1.Springboot_Exercise.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class SalaryDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double basicSalary;
    private double hra;
    private double bonus;
    private double deductions;
    private double netSalary;
    private double total;

    @OneToOne
    @JoinColumn(name="emp_Id")
    @JsonIgnore
    private Employee employee;

    public SalaryDetail() {
    }

    public SalaryDetail(Long id, double basicSalary, double hra, double deductions, Employee employee) {
        this.id = id;
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.bonus = total*0.10;
        this.deductions = deductions;
        this.total = basicSalary + hra + bonus + deductions;
        this.netSalary = total - deductions;
        this.employee = employee;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getHra() {
        return hra;
    }

    public void setHra(double hra) {
        this.hra = hra;
    }

    public double getBonus() {
        return total*0.10;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getDeductions() {
        return deductions;
    }
    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getNetSalary() {
        return (basicSalary+hra+bonus)-deductions;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setNetSalary(double netSalary) {
        this.netSalary = netSalary;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
