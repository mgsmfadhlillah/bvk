package com.bvk.springjwt.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_user")
public class AppUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id", updatable = true, nullable = false)
  private Integer userId;

  @Column(name = "fullname")
  private String fullname;

  @Column(name ="email")
  private String email;

  @JsonIgnore
  @Column(name = "encrypted_password")
  private String password;

  @JsonIgnore
  @Column(name = "enabled")
  private Integer enabled;
}
