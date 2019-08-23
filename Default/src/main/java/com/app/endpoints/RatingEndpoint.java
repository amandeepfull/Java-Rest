package com.app.endpoints;

import com.app.baseEndpoints.AbstractBaseEndpoint;
import com.app.model.Hotel;
import com.app.response.ApiResponse;
import com.app.services.RateService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/v1/rate")
public class RatingEndpoint extends AbstractBaseEndpoint {

    @Path("/hotel/{hotelId}")
    @GET
    public Response rateHotel(@PathParam("hotelId") String hotelId, @QueryParam("rating") float rating){

        ApiResponse response = new ApiResponse();

        response.setOk(new RateService().rateHotel(hotelId, rating));

        return Response.ok(response).build();
    }
}
