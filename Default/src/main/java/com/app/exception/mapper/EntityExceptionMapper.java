package com.app.exception.mapper;

import com.app.exception.EntityException;
import com.commons.Enum.EntityErrorCode;
import com.commons.Enum.ErrorCode;
import com.commons.response.ApiResponse;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import static com.commons.Enum.ApiErrorCode.INTERNAL_SERVER_ERROR;

public class EntityExceptionMapper implements ExceptionMapper<EntityException> {

    @Override
    public Response toResponse(EntityException e) {

        ApiResponse resp = new ApiResponse(false, e.getError() != null ? e.getError() : INTERNAL_SERVER_ERROR, e.getMessage() == null ? "something went wrong on our end" : e.getMessage());
        return Response.status(getStatus(e.getError())).entity(resp).type(MediaType.APPLICATION_JSON).build();
    }

    private Response.Status getStatus(ErrorCode errorCode) {

        if (errorCode == null) {
            return Response.Status.INTERNAL_SERVER_ERROR;
        }

        if (errorCode instanceof EntityErrorCode) {
            EntityErrorCode entityErrorCode = (EntityErrorCode) errorCode;

            switch (entityErrorCode) {
                case ALREADY_EXISTS:
                case ALREADY_DELETED:
                case ALREADY_DISABLED:
                    return Response.Status.BAD_REQUEST;
                case NOT_FOUND:
                    return Response.Status.NOT_FOUND;
                case CREATE_FAILED:
                case DELETE_FAILED:
                case UPDATE_FAILED:
                case DISABLED_FAILED:
                default:
                    return Response.Status.INTERNAL_SERVER_ERROR;
            }
        }

        return Response.Status.INTERNAL_SERVER_ERROR;
    }

}

