package com.bvk.springjwt.services.impl;

import com.bvk.springjwt.models.Product;
import com.bvk.springjwt.payload.response.PagingHeaders;
import com.bvk.springjwt.payload.response.PagingResponse;
import com.bvk.springjwt.repository.ProductRepository;
import com.bvk.springjwt.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired private ProductRepository productRepo;

  public PagingResponse get(Specification<Product> spec, HttpHeaders headers, Sort sort) {
    if (isRequestPaged(headers)) {
      return getWithPages(spec, buildPageRequest(headers, sort));
    } else {
      List<Product> entities = getWithoutPages(spec, sort);
      return new PagingResponse((long) entities.size(), 0L, 0L, 0L, 0L, entities);
    }
  }
  public PagingResponse getWithPages(Specification<Product> spec, Pageable pageable) {
    Page<Product> page = productRepo.findAll(spec, pageable);
    List<Product> content = page.getContent();
    return new PagingResponse(page.getTotalElements(), (long) page.getNumber(), (long) page.getNumberOfElements(), pageable.getOffset(), (long) page.getTotalPages(), content);
  }

  public List<Product> getWithoutPages(Specification<Product> spec, Sort sort) {
    return productRepo.findAll(spec, sort);
  }

  private boolean isRequestPaged(HttpHeaders headers) {
    return headers.containsKey(PagingHeaders.PAGE_NUMBER.getName()) && headers.containsKey(PagingHeaders.PAGE_SIZE.getName());
  }

  private Pageable buildPageRequest(HttpHeaders headers, Sort sort) {
    int page = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_NUMBER.getName())).get(0));
    int size = Integer.parseInt(Objects.requireNonNull(headers.get(PagingHeaders.PAGE_SIZE.getName())).get(0));
    return PageRequest.of(page, size, sort);
  }
}
