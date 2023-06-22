package com.totemcrew.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
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
