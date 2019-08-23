package com.app.endpoints;

import com.app.baseEndpoints.AbstractBaseEndpoint;
import com.app.daoImpl.HotelDaoImpl;
import com.app.entity.Hotel;
import com.app.response.ApiResponse;
import com.app.services.SearchService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @GET
    @Path("/{hotelId}/rate")
    public Response rateHotel(@PathParam("hotelId") String hotelId, @QueryParam("rating") float rating){

        ApiResponse response = new ApiResponse();

        response.setOk(new HotelDaoImpl().rateHotel(hotelId, rating));

        return Response.ok(response).build();
    }

    @Path("/search/food")
    @GET
    public Response searchHotel(@QueryParam("searchStr") String searchStr) {

        ApiResponse response = new ApiResponse();

        List<Hotel> hotels = new SearchService().searchHotelByFood(searchStr);
        response.setOk(true);
        response.add("hotels", hotels);
        return Response.ok(response).build();
    }
}
