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
@Table(name = "product")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "product_id", length = 11, nullable = false)
  private Integer productId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "store_id", nullable = false)
  @ToString.Exclude
  private Store store;

  @Column(name = "product_name", length = 150, nullable = false)
  private String productName;

  @Column(name = "product_price", length = 8, nullable = false)
  private BigDecimal productPrice;

  @Column(name = "product_stock", length = 5, nullable = false)
  private Integer productStock;
}
