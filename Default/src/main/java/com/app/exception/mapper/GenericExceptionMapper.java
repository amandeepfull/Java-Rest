package com.app.exception.mapper;


import com.app.exception.ApiErrorCode;
import com.app.response.ApiResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable ex) {

        System.out.println("Generic Exception : {}"+ ex.getMessage());

        ex.printStackTrace();


        ApiResponse response = new ApiResponse(false, ApiErrorCode.INTERNAL_SERVER_ERROR, "something went wrong on our end");

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).type("application/json").build();
    }
}
