package com.bvk.springjwt.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "app_role")
public class AppRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "role_id")
  private Integer roleId;

  @Column(name = "role_name")
  private String roleName;
}
