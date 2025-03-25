package com.springbootExercise1.Springboot_Exercise.DTO;

public class SalaryRangeDTO {
    private double minSalary;
    private double maxSalary;

    public SalaryRangeDTO() {
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }
}
