package com.foody.order.dao;

import com.foody.order.entities.Order;

public interface OrderDao {
    Order orderCart(Order cart) throws Exception;
}
