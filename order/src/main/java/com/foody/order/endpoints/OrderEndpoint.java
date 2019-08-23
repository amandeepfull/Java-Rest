package com.foody.order.endpoints;

import com.foody.order.entities.Order;
import com.foody.order.response.ApiResponse;
import com.foody.order.baseEndpoints.AbstractBaseEndpoint;
import com.foody.order.daoImpl.OrderDaoImpl;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/v1/order")
public class OrderEndpoint extends AbstractBaseEndpoint {

    @POST
    @Path("/cart/{cartId}")
    public Response orderCart(@PathParam("cartId") String cartId, Order order) {

        ApiResponse response = new ApiResponse();

        if (cartId == null)
            throw new IllegalArgumentException("invalid cartId");

        if (order == null) throw new IllegalArgumentException("invalid payment");

        order.setCartId(cartId);

        order = new OrderDaoImpl().orderCart(order);

        response.setOk(true);
        response.add("payment", order);
        return Response.ok(response).build();
    }

}
