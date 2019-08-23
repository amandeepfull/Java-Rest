package com.authserver.app.endpoints;



import com.authserver.app.baseEndpoints.AbstractBaseEndpoint;
import com.authserver.app.response.ApiResponse;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class TestEndpoint extends AbstractBaseEndpoint {

    @Path("/test")
    @GET
    public Response getData(){

        ApiResponse response = new ApiResponse();

        response.add("test", "data");

        response.setOk(true);
        return Response.ok(response).build();

    }
}
