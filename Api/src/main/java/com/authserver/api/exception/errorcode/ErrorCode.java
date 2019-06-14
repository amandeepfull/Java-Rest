package com.authserver.api.exception.errorcode;

import com.fasterxml.jackson.annotation.JsonValue;

public interface ErrorCode {

	@JsonValue
	String getErrorCode();

	String toString();
}
