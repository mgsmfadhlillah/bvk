package com.bvk.springjwt.services;

import com.bvk.springjwt.payload.request.GeneralRequest;
import com.bvk.springjwt.payload.response.MessageResponse;

public interface TransactionsService {
  MessageResponse finalizeCart(GeneralRequest request);
}
