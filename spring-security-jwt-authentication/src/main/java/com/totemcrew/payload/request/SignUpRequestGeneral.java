package com.totemcrew.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class SignUpRequestGeneral {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;
    @NotBlank
    @Size(min = 3, max = 20)
    private String first_name;
    @NotBlank
    @Size(min = 3, max = 20)
    private String last_name;
    @NotBlank
    @Size(min = 3, max = 20)
    private String phone;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String parentFullName;

    private String phoneParent;

    private Long userId;
    private Long sectionId;

    private String specialty;

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }


    public String getSpecialty() {
        return specialty;
    }


    public void setParentFullName(String parentFullName) {
        this.parentFullName = parentFullName;
    }

    public void setPhoneParent(String phoneParent) {
        this.phoneParent = phoneParent;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public String getParentFullName() {
        return parentFullName;
    }

    public String getPhoneParent() {
        return phoneParent;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getFirst_name() { return first_name; }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getLast_name() { return last_name; }

    public void getLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getPhone() { return phone; }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
