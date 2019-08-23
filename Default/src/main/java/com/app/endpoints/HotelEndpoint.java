package com.app.endpoints;

import com.app.baseEndpoints.AbstractBaseEndpoint;
import com.app.daoImpl.HotelDaoImpl;
import com.app.model.Food;
import com.app.model.Hotel;
import com.app.response.ApiResponse;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/v1/hotel")
public class HotelEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response addHotel(Hotel hotel) {

        ApiResponse response = new ApiResponse();

        hotel = new HotelDaoImpl().add(hotel);

        response.setOk(true);
        response.add("hotel", hotel);
        return Response.ok(response).build();
    }
}
