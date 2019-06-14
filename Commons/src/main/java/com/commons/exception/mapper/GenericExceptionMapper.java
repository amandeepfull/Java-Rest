package com.commons.exception.mapper;


import com.commons.Enum.ApiErrorCode;
import com.commons.constants.CommonConstants;
import com.commons.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;

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

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).type(CommonConstants.JSON_CONTENTTYPE).build();
    }
}
