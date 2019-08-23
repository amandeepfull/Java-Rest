package com.app.exception.mapper;


import com.app.exception.ApiErrorCode;
import com.app.exception.runtime.IllegalArgException;
import com.app.response.ApiResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class IllegalArgExceptionMapper implements ExceptionMapper<IllegalArgException> {

    @Override
    public Response toResponse(IllegalArgException e) {

        ApiResponse resp = new ApiResponse(false, e.getError() != null ? e.getError() : ApiErrorCode.BAD_REQUEST, e.getMessage() == null ? "that was a bad request" : e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(resp).type(MediaType.APPLICATION_JSON).build();
    }
}
