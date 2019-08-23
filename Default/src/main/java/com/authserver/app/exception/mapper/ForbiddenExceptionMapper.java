package com.authserver.app.exception.mapper;

import com.authserver.app.exception.ForbiddenException;
import com.commons.Enum.ApiErrorCode;
import com.commons.response.ApiResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException> {

    @Override
    public Response toResponse(ForbiddenException e) {

        ApiResponse resp = new ApiResponse(false, ApiErrorCode.FORBIDDEN_REQUEST, e.getMessage() == null ? "you are not authorized for this action" : e.getMessage());
        return Response.status(Response.Status.FORBIDDEN).entity(resp).type(MediaType.APPLICATION_JSON).build();
    }
}
