package com.bvk.springjwt.repository;

import com.bvk.springjwt.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
  List<Cart> findByCartIdInAndStatusEqualsAndUser_UserId(List<Integer> id, String status, Integer userId);
  List<Cart> findByUser_EmailEqualsAndStatusEquals(String email, String status);
}
