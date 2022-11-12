package com.bvk.springjwt.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cart_id", length = 11, nullable = false)
  private Integer cartId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  @ToString.Exclude
  private Product product;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @ToString.Exclude
  private AppUser user;

  @Column(name = "total_items", length = 3, nullable = false)
  private Integer totalItems;

  @Column(name = "total_price", length = 10, nullable = false)
  private BigDecimal totalPrice;

  @Column(name = "status", length = 10)
  private String status;

  public Cart(Product product, AppUser user, Integer totalItems, BigDecimal totalPrice, String status) {
    this.product = product;
    this.user = user;
    this.totalItems = totalItems;
    this.totalPrice = totalPrice;
    this.status = status;
  }
}
