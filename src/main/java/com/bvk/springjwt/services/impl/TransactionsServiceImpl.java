package com.bvk.springjwt.services.impl;

import com.bvk.springjwt.models.AppUser;
import com.bvk.springjwt.models.Cart;
import com.bvk.springjwt.models.Product;
import com.bvk.springjwt.models.Transactions;
import com.bvk.springjwt.payload.request.GeneralRequest;
import com.bvk.springjwt.payload.response.MessageResponse;
import com.bvk.springjwt.repository.CartRepository;
import com.bvk.springjwt.repository.ProductRepository;
import com.bvk.springjwt.repository.TransactionsRepository;
import com.bvk.springjwt.repository.UserRepository;
import com.bvk.springjwt.services.TransactionsService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TransactionsServiceImpl implements TransactionsService {
  @Autowired private CartRepository cartRepo;
  @Autowired private TransactionsRepository trxRepo;
  @Autowired private UserRepository userRepo;
  @Autowired private ProductRepository productRepo;

  @Transactional
  public MessageResponse finalizeCart(GeneralRequest request) {
    JSONArray json = new JSONArray(request.getData_request());

    Optional<AppUser> user = userRepo.getAppUsersByEmail(request.getEmail());
    List<Cart> cart = cartRepo.findByCartIdInAndStatusEqualsAndUser_UserId(listCartIds(json), "In Cart", user.get().getUserId());
    if(!cart.isEmpty()){
      for(Cart cr : cart){
        Optional<Product> product = productRepo.findById(cr.getProduct().getProductId());
        product.ifPresent((p) -> {
          System.out.println(cr.getTotalItems()+"-"+p.getProductStock());
          if(cr.getTotalItems() < p.getProductStock()){
            p.setProductStock(p.getProductStock() - cr.getTotalItems());
            cr.setStatus("Finalize");
            cr.setProduct(p);
          }else{
            throw new IllegalArgumentException("Product is Out of Stock");
          }
          trxRepo.save(new Transactions(user.get(), cr, cr.getTotalPrice(), new Date()));
        });
      }
    }else{
      throw new IllegalArgumentException("Cart Id Not Found");
    }

    return new MessageResponse(200, "Success", new HashMap<>());
  }

  private ArrayList<Integer> listCartIds(JSONArray arr){
    ArrayList<Integer> IDs = new ArrayList<Integer>();
    if (arr != null) {
      for (int i=0;i<arr.length();i++){
        IDs.add(arr.getInt(i));
      }
    }
    return IDs;
  }
}
