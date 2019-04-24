package com.commons.exception.mapper;


import com.commons.Enum.ApiErrorCode;
import com.commons.exception.runtime.IllegalArgException;
import com.commons.response.ApiResponse;

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
