package com.totemcrew.payload.request;

public class CreateTeacherResource {
    private String speciality;
    private double salary;
    private Long userId;

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSpeciality() {
        return speciality;
    }

    public double getSalary() {
        return salary;
    }

    public Long getUserId() {
        return userId;
    }
}
