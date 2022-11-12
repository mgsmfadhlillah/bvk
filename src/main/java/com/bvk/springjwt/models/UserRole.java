package com.bvk.springjwt.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_role")
public class UserRole {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "userrole_id")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private AppUser appUser;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", nullable = false)
  private AppRole appRole;
}
