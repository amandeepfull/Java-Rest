package com.app.endpoints;

import com.app.baseEndpoints.AbstractBaseEndpoint;
import com.app.model.Hotel;
import com.app.response.ApiResponse;
import com.app.services.SearchService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/search")
public class SearchEndpoint extends AbstractBaseEndpoint {

    @Path("/hotel/food")
    @GET
    public Response searchHotel(@QueryParam("searchStr") String searchStr) {

        ApiResponse response = new ApiResponse();

        List<Hotel> hotels = new SearchService().searchHotelByFood(searchStr);
        response.setOk(true);
        response.add("hotels", hotels);
        return Response.ok(response).build();
    }
}
