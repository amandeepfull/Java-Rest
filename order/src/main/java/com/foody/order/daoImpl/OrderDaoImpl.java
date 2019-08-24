package com.foody.order.daoImpl;

import com.foody.order.baseEndpoints.objectify.OfyService;
import com.foody.order.dao.OrderDao;
import com.foody.order.entities.Cart;
import com.foody.order.entities.Order;
import com.foody.order.enums.PaymentType;
import com.foody.order.ext.FoodService;
import com.foody.order.ext.PaymentService;
import com.foody.order.model.Food;
import com.foody.order.utils.ObjUtil;

import java.util.List;
import java.util.UUID;

public class OrderDaoImpl extends OfyService implements OrderDao {


    @Override
    public Order orderCart(Order order) {

        validateOrder(order);
        Cart cart = get(Cart.class, order.getCartId());

        if(cart == null)
            throw new IllegalArgumentException("Invalid cart");

        List<Food> foods =  new FoodService().getFoods(cart.getFoodIds());
        double totalAmt =  Food.getFoodsTotalPrice(foods);

        order.setId(UUID.randomUUID().toString());
        new PaymentService().proceedForPayment(cart.getId(), order.getId(), totalAmt, order.getPaymentType());

        order.setTotalAmt(totalAmt);

        return save(order) != null ? order : null;
    }

    private void validateOrder(Order order) {
        if(order == null)
            throw new IllegalArgumentException("invalid payment");

        if(ObjUtil.isBlank(order.getAddress()))
            throw new IllegalArgumentException("address is not available");

        if(order.getPaymentType() == null)
            throw new IllegalArgumentException("payment type is not available");

        if(ObjUtil.isBlank(order.getCartId()))
            throw new IllegalArgumentException("invalid cartid");

    }
}
