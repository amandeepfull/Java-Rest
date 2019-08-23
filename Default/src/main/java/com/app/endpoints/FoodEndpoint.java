package com.app.endpoints;

import com.app.baseEndpoints.AbstractBaseEndpoint;
import com.app.daoImpl.FoodDaoImpl;
import com.app.entity.Food;
import com.app.response.ApiResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/v1/food")
public class FoodEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response addFood(Food food){

        ApiResponse response = new ApiResponse();

       food =  new FoodDaoImpl().add(food);

        response.setOk(true);
        response.add("food", food);

        return Response.ok(response).build();
    }
}
