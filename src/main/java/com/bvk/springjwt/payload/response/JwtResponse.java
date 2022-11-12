package com.bvk.springjwt.payload.response;

import java.util.List;

public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Integer id;
  private String username;
  private String fullname;
  private List<String> roles;

  public JwtResponse(String accessToken, Integer id, String username, String fullname, List<String> roles) {
    this.token = accessToken;
    this.id = id;
    this.fullname = fullname;
    this.username = username;
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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getFullname(){ return fullname; }

  public void setFullname(String fullname) { this.fullname = fullname; }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }
}
