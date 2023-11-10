package com.example.childrens_world.service;

import com.example.childrens_world.model.ShoppingCart;
import com.example.childrens_world.model.dto.ChargeRequest;

public interface ShoppingCartService {
    ShoppingCart findActiveShoppingCartByUsername(String username);
    ShoppingCart createNewShoppingCart(String username);
    ShoppingCart addProductToShoppingCart(String username, Long productId);
    ShoppingCart removeProductFromShoppingCart(String username, Long productId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart cancelActiveShoppingCart(String username);
    ShoppingCart checkoutShoppingCart(String username, ChargeRequest chargeRequest);
}
