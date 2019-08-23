package com.app.exception;


import com.commons.Enum.ErrorCode;


public class ForbiddenException extends AbstractException {

    public ForbiddenException() {
        super();
    }

    public ForbiddenException(String message) {
        super(message);
    }

    public ForbiddenException(ErrorCode error, String msg) {
        super(error, msg);
    }

    public ForbiddenException(ErrorCode error, String msg, Object info) {
        super(error, msg, info);
    }
}
