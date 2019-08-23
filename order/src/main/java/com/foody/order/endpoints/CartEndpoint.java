package com.foody.order.endpoints;

import com.foody.order.baseEndpoints.AbstractBaseEndpoint;
import com.foody.order.daoImpl.CartDaoImpl;
import com.foody.order.entities.Cart;
import com.foody.order.response.ApiResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/v1/cart")
public class CartEndpoint extends AbstractBaseEndpoint {

    @POST
    @Path("/{cartId}/food/{foodId}")
    public Response addFoodToCart(@PathParam("cartId") String cartId, @PathParam("foodId") String foodId){


        ApiResponse response = new ApiResponse();


        Cart cart =  new CartDaoImpl().addFoodToCart(cartId,foodId);

        response.setOk(true);
        response.add("cart", cart);
        return Response.ok(response).build();
    }
}
