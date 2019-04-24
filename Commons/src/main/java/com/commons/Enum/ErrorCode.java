package com.commons.Enum;

import com.fasterxml.jackson.annotation.JsonValue;

public interface ErrorCode {

	@JsonValue
	String getErrorCode();

	String toString();
}
