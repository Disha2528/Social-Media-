package com.springbootExercise1.Springboot_Exercise.DTO;

public class SalaryDetailDTO {
    private int empId;
    private double basicSalary;
    private double hra;
    private double bonus;
    private double deductions;

    public SalaryDetailDTO() {
    }

    public SalaryDetailDTO(int empId, double basicSalary, double hra, double bonus, double deductions) {
        this.empId = empId;
        this.basicSalary = basicSalary;
        this.hra = hra;
        this.bonus = bonus;
        this.deductions = deductions;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
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
        return bonus;
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
}
