package com.app.exception.mapper;


import com.commons.Enum.ApiErrorCode;
import com.commons.response.ApiResponse;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {

    @Override
    public Response toResponse(InvalidFormatException exception) {

        ApiResponse response = new ApiResponse(false, ApiErrorCode.BAD_REQUEST, "invalid format, please check the json property value");

        return Response.status(Response.Status.BAD_REQUEST).type("application/json").entity(response).build();
    }
}
