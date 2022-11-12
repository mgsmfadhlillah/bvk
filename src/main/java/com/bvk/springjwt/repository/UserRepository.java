package com.bvk.springjwt.repository;

import com.bvk.springjwt.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
  @Query(value = "select * from app_user where email = ?1", nativeQuery = true)
  Optional<AppUser> findByEmail(String username);
  Optional<AppUser> getAppUsersByEmail(String username);
  Optional<AppUser> findByEmailAndPassword(String uname, String pass);

  Boolean existsByEmail(String email);
}
