package com.authserver.api.exception.mapper;

import com.commons.Enum.ApiErrorCode;
import com.commons.Enum.EntityErrorCode;
import com.commons.Enum.ErrorCode;
import com.commons.exception.*;
import com.commons.response.ApiResponse;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

public class ExceptionMapper {

    protected final Logger logger = LogManager.getLogger(getClass());


    // Generic Exception Handler
    @ExceptionHandler({ Throwable.class })
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatch(
            Throwable ex, WebRequest request) {

        ApiResponse resp = new ApiResponse(false, ApiErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage() == null ? "something went wrong on our end" : ex.getMessage());
        return new ResponseEntity<ApiResponse>(resp, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    // Entity Exception Handler
    @ExceptionHandler({ EntityException.class })
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatch(
            EntityException ex, WebRequest request) {

        ApiResponse resp = new ApiResponse(false, ex.getError() != null ? ex.getError() : ApiErrorCode.INTERNAL_SERVER_ERROR, ex.getMessage() == null ? "something went wrong on our end" : ex.getMessage());
        return new ResponseEntity<ApiResponse>(resp, new HttpHeaders(), getStatus(ex.getError()));
    }


    // Forbidden Exception handler
    @ExceptionHandler({ ForbiddenException.class })
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatch(
            ForbiddenException ex, WebRequest request) {

        ApiResponse resp = new ApiResponse(false, ApiErrorCode.FORBIDDEN_REQUEST, ex.getMessage() == null ? "you are not authorized for this action" : ex.getMessage());
        return new ResponseEntity<ApiResponse>(resp, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }


    // NotFound Exception handler
    @ExceptionHandler({ NotFoundException.class })
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatch(
            NotFoundException ex, WebRequest request) {

        ApiResponse resp = new ApiResponse(false, ApiErrorCode.NOT_FOUND, ex.getMessage() == null ? "resource  not found" : ex.getMessage());
        return new ResponseEntity<ApiResponse>(resp, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // IllegalArgException handler
    @ExceptionHandler({ IllegalArgException.class })
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatch(
            IllegalArgException ex, WebRequest request) {

        ApiResponse resp = new ApiResponse(false, ex.getError() != null ? ex.getError() : ApiErrorCode.BAD_REQUEST, ex.getMessage() == null ? "it was bad request" : ex.getMessage());
        return new ResponseEntity<ApiResponse>(resp, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // UnauthorizedException handler
    @ExceptionHandler({UnauthorizedException.class })
    public ResponseEntity<ApiResponse> handleMethodArgumentTypeMismatch(
            UnauthorizedException ex, WebRequest request) {

        ApiResponse resp = new ApiResponse(false, ApiErrorCode.UNAUTHORIZED_REQUEST, ex.getMessage() == null ? "you are not authorized for this action" : ex.getMessage());
        return new ResponseEntity<ApiResponse>(resp, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }


    private HttpStatus getStatus(ErrorCode errorCode) {

        if (errorCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        if (errorCode instanceof EntityErrorCode) {
            EntityErrorCode entityErrorCode = (EntityErrorCode) errorCode;

            switch (entityErrorCode) {
                case ALREADY_EXISTS:
                case ALREADY_DELETED:
                case ALREADY_DISABLED:
                    return HttpStatus.BAD_REQUEST;
                case NOT_FOUND:
                    return HttpStatus.NOT_FOUND;
                case CREATE_FAILED:
                case DELETE_FAILED:
                case UPDATE_FAILED:
                case DISABLED_FAILED:
                default:
                    return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
