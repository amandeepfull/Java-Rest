package com.app.exception;


import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiErrorModel implements Serializable {

    private String code;
    private String message;

    public ApiErrorModel(ErrorCode errorCode, String message) {

        this.code = errorCode != null ? errorCode.toString() : null;
        this.message = message;
    }

    public ApiErrorModel(String errorCode, String message) {

        this.code = errorCode;
        this.message = message;
    }
}

