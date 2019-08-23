package com.foody.order.daoImpl;

import com.foody.order.baseEndpoints.objectify.OfyService;
import com.foody.order.entities.Cart;
import com.foody.order.dao.CartDao;

public class CartDaoImpl extends OfyService implements CartDao {
    @Override
    public Cart addFoodToCart(String cartId, String foodId) {

        Cart cart = get(Cart.class, cartId);

        if(cart == null){  // dumping the cart data for a user
            cart = new Cart();
            cart.setId("anonomous");
        }

        cart.addFood(foodId);
        return  save(cart) != null ? cart : null;
    }
}
