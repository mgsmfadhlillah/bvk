package com.bvk.springjwt.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions")
public class Transactions {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trx_id", length = 11, nullable = false)
  private Integer trx_id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  @ToString.Exclude
  private AppUser user;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cart_id", nullable = false)
  @ToString.Exclude
  private Cart cart;

  @Column(name = "grand_total_price", length = 9,nullable = false)
  private BigDecimal grandTotalPrice;

  @Column(name = "trx_date")
  private Date trxDate;

  public Transactions(AppUser user, Cart cart, BigDecimal grandTotalPrice, Date trxDate) {
    this.user = user;
    this.cart = cart;
    this.grandTotalPrice = grandTotalPrice;
    this.trxDate = trxDate;
  }
}
