package com.app.exception.mapper;



import com.commons.Enum.EntityErrorCode;
import com.commons.response.ApiResponse;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {

        //TODO set response content type based on accept header

        ApiResponse response = new ApiResponse(false, EntityErrorCode.NOT_FOUND, e.getMessage() != null ? e.getMessage() : "the request resource was not found on server");
        return Response.status(Response.Status.NOT_FOUND).type("application/json").entity(response).build();
    }
}
