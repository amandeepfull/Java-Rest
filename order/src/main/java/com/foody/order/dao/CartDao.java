package com.foody.order.dao;

import com.foody.order.entities.Cart;

public interface CartDao {
    Cart addFoodToCart(String cartId, String foodId);
}
