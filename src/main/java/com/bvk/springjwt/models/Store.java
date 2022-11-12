package com.bvk.springjwt.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "store_id", length = 11, nullable = false)
  private Integer storeId;
  @Column(name = "store_name", length = 100, nullable = false)
  private String storeName;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Store store = (Store) o;
    return storeId != null && Objects.equals(storeId, store.storeId);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
