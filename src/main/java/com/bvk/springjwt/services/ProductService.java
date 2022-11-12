package com.bvk.springjwt.services;

import com.bvk.springjwt.models.Product;
import com.bvk.springjwt.payload.response.PagingResponse;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;

public interface ProductService {
  PagingResponse get(Specification<Product> spec, HttpHeaders headers, Sort sort);
}
