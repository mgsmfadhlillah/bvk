package com.bvk.springjwt.repository;

import com.bvk.springjwt.models.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer> {

  @Query(value = "select r.role_name from app_role r join user_role ur on ur.role_id = r.role_id join app_user u on u.user_id = ur.user_id where u.user_id = :userId", nativeQuery = true)
  List<String> getRoleNames(@Param("userId") Integer userId);
//  Iterable<AppRole> findAllByRoleId(Integer id);
  AppRole findByRoleName(String role);
//  Optional<AppRole> findByRoleName(String role);
}