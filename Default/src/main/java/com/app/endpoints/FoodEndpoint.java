package com.app.endpoints;

import com.app.baseEndpoints.AbstractBaseEndpoint;
import com.app.daoImpl.FoodDaoImpl;
import com.app.entity.Food;
import com.app.response.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Set;

@Path("/v1/food")
public class FoodEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response addFood(Food food) {

        ApiResponse response = new ApiResponse();

        food = new FoodDaoImpl().add(food);

        response.setOk(true);
        response.add("food", food);

        return Response.ok(response).build();
    }

    @POST
    @Path("/total/price")
    public Response getTotalPrice(Set<String> foodIds) {

        ApiResponse response = new ApiResponse();

        response.add("foodsTotalAmt",new FoodDaoImpl().getTotalPrice(foodIds));
        response.setOk(true);

        return Response.ok(response).build();
    }
}
