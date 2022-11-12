package com.bvk.springjwt.controllers;

import com.bvk.springjwt.models.Product;
import com.bvk.springjwt.payload.request.GeneralRequest;
import com.bvk.springjwt.payload.response.MessageResponse;
import com.bvk.springjwt.payload.response.PagingHeaders;
import com.bvk.springjwt.payload.response.PagingResponse;
import com.bvk.springjwt.services.CartService;
import com.bvk.springjwt.services.ProductService;
import com.bvk.springjwt.services.TransactionsService;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/items")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CartController {
  @Autowired private TransactionsService trxService;
  @Autowired private ProductService productService;
  @Autowired private CartService cartService;

  @PreAuthorize("hasRole('USER')")
  @RequestMapping(path = {"/get/list"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
  public ResponseEntity<List<Product>> getProductList(@And({
      @Spec(path="productName", params="productName", spec = Like.class)
  }) Specification<Product> spec, Sort sort, @RequestHeader HttpHeaders headers) {
    final PagingResponse response = productService.get(spec, headers, sort);
    return new ResponseEntity<>(response.getElements(), returnHttpHeaders(response), HttpStatus.OK);
  }

  @PreAuthorize("hasRole('USER')")
  @RequestMapping(path = {"list-in-cart"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public ResponseEntity<Object> productListInCart(@Valid @RequestBody GeneralRequest request){
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    request.setEmail(userDetails.getUsername());
    final MessageResponse response = cartService.listInCart(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('USER')")
  @RequestMapping(path = {"add-to-cart"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public ResponseEntity<Object> productAddToCart(@Valid @RequestBody GeneralRequest request){
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    request.setEmail(userDetails.getUsername());
    final MessageResponse response = cartService.addToCart(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('USER')")
  @RequestMapping(path = {"remove-from-cart"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public ResponseEntity<Object> productRemoveFromCart(@Valid @RequestBody GeneralRequest request){
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    request.setEmail(userDetails.getUsername());
    final MessageResponse response = cartService.removeFromCart(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('USER')")
  @RequestMapping(path = {"finalize-cart"}, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
  public ResponseEntity<Object> finalizeItemsInCart(@Valid @RequestBody GeneralRequest request){
    UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    request.setEmail(userDetails.getUsername());
    final MessageResponse response = trxService.finalizeCart(request);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  public HttpHeaders returnHttpHeaders(PagingResponse response) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(PagingHeaders.COUNT.getName(), String.valueOf(response.getCount()));
    headers.set(PagingHeaders.PAGE_SIZE.getName(), String.valueOf(response.getPageSize()));
    headers.set(PagingHeaders.PAGE_OFFSET.getName(), String.valueOf(response.getPageOffset()));
    headers.set(PagingHeaders.PAGE_NUMBER.getName(), String.valueOf(response.getPageNumber()));
    headers.set(PagingHeaders.PAGE_TOTAL.getName(), String.valueOf(response.getPageTotal()));
    return headers;
  }
}
