package com.commons.exception.mapper;


import com.commons.Enum.ApiErrorCode;
import com.commons.response.ApiResponse;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;


public class JaxRsForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(ForbiddenException e) {
        ApiResponse resp = new ApiResponse(false, ApiErrorCode.FORBIDDEN_REQUEST, e.getMessage() == null ? "you are not authorized for this action" : e.getMessage());
        return Response.status(Response.Status.FORBIDDEN).entity(resp).type(MediaType.APPLICATION_JSON).build();
    }
}
