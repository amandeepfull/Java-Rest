package com.commons.exception.mapper;


import com.commons.Enum.ApiErrorCode;
import com.commons.response.ApiResponse;

import javax.ws.rs.NotAllowedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class NotAllowedExceptionMapper implements ExceptionMapper<NotAllowedException> {

    @Override
    public Response toResponse(NotAllowedException e) {

        ApiResponse response = new ApiResponse(false, ApiErrorCode.METHOD_NOT_ALLOWED, e.getMessage() != null ? e.getMessage() : "the requested method is not allowed for the endpoint");
        return Response.status(Response.Status.METHOD_NOT_ALLOWED).type("application/json").entity(response).build();
    }
}
