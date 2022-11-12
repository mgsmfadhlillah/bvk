package com.bvk.springjwt.services;

import com.bvk.springjwt.payload.request.GeneralRequest;
import com.bvk.springjwt.payload.response.MessageResponse;

public interface CartService {
  MessageResponse addToCart(GeneralRequest request);
  MessageResponse removeFromCart(GeneralRequest request);
  MessageResponse listInCart(GeneralRequest request);
}
