package com.bvk.springjwt.services.impl;

import com.bvk.springjwt.models.AppUser;
import com.bvk.springjwt.models.Cart;
import com.bvk.springjwt.models.Product;
import com.bvk.springjwt.payload.request.GeneralRequest;
import com.bvk.springjwt.payload.response.MessageResponse;
import com.bvk.springjwt.repository.CartRepository;
import com.bvk.springjwt.repository.ProductRepository;
import com.bvk.springjwt.repository.UserRepository;
import com.bvk.springjwt.services.CartService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
  @Autowired private CartRepository cartRepo;
  @Autowired private ProductRepository productRepo;
  @Autowired private UserRepository userRepo;

  public MessageResponse listInCart(GeneralRequest request){
    List<Cart> cart = cartRepo.findByUser_EmailEqualsAndStatusEquals(request.getEmail(), "In Cart");
    Map<String, Object> map = new HashMap<String, Object>();
    if(!cart.isEmpty()){
//      map = cart.stream().collect(Collectors.toMap(Cart::getStatus, Function.identity()));
      map.put("data", cart);
    }else{
      throw new IllegalArgumentException("Cart Id Not Found");
    }
    return new MessageResponse(200, "Success", map);
  }
  @Transactional
  public MessageResponse addToCart(GeneralRequest request){
    JSONObject json = new JSONObject(request.getData_request());
    Optional<AppUser> user = userRepo.getAppUsersByEmail(request.getEmail());
    Optional<Product> product = productRepo.findById(json.getInt("productId"));
    product.ifPresentOrElse((p) -> {
      int totalItems = json.getInt("totalItems");
      cartRepo.save(new Cart(p, user.get(), totalItems, p.getProductPrice().multiply(BigDecimal.valueOf(totalItems)), "In Cart"));
    }, () -> {
      throw new IllegalArgumentException("Product Id Not Found");
    });
    return new MessageResponse(200, "Success", new HashMap<>());
  }

  @Transactional
  public MessageResponse removeFromCart(GeneralRequest request){
    JSONObject json = new JSONObject(request.getData_request());
    Optional<Cart> cart = cartRepo.findById(json.getInt("cartId"));
    cart.ifPresentOrElse((c) -> {
      c.setStatus("Removed");
      cartRepo.save(c);
    }, () -> {
      throw new IllegalArgumentException("Cart Id Not Found");
    });
    return new MessageResponse(200, "Success", new HashMap<>());
  }
}
