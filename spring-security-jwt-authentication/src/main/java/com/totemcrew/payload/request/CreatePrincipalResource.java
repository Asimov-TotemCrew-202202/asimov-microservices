package com.totemcrew.payload.request;

public class CreatePrincipalResource {

    private String specialty;
    private double experienceYears;
    private double salary;
    private Long userId;

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setExperienceYears(double experienceYears) {
        this.experienceYears = experienceYears;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSpecialty() {
        return specialty;
    }

    public double getExperienceYears() {
        return experienceYears;
    }

    public double getSalary() {
        return salary;
    }

    public Long getUserId() {
        return userId;
    }
}
