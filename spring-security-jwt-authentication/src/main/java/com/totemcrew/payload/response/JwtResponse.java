package com.totemcrew.payload.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  private String username;
  private String email;
  private List<String> roles;

  private String first_name;
  private String last_name;
  private String phone;


  public JwtResponse(String accessToken, Long id, String username, String email,  String first_name, String last_name, String phone,List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.username = username;
    this.email = email;
    this.first_name = first_name;
    this.last_name = last_name;
    this.phone = phone;
    this.roles = roles;

  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }
  public String getFirst_name() {
    return first_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }
  public String getLast_name() {
    return last_name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }
  public List<String> getRoles() {
    return roles;
  }
}
