package com.foody.order.daoImpl;

import com.foody.order.baseEndpoints.objectify.OfyService;
import com.foody.order.dao.OrderDao;
import com.foody.order.entities.Cart;
import com.foody.order.entities.Order;
import com.foody.order.utils.ObjUtil;

public class OrderDaoImpl extends OfyService implements OrderDao {


    @Override
    public Order orderCart(Order order) {

        validateOrder(order);
        Cart cart = get(Cart.class, order.getCartId());

        if(cart == null)
            throw new IllegalArgumentException("Invalid cart");


        return null;


        // payment should be processed here.

    }

    private void validateOrder(Order order) {
        if(order == null)
            throw new IllegalArgumentException("invalid order");

        if(ObjUtil.isBlank(order.getAddress()))
            throw new IllegalArgumentException("address is not available");

        if(order.getPayment() == null)
            throw new IllegalArgumentException("payment type is not available");

        if(ObjUtil.isBlank(order.getCartId()))
            throw new IllegalArgumentException("invalid cartid");

    }
}
