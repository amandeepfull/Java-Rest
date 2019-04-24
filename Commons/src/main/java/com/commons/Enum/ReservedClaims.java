package com.commons.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ReservedClaims {

    USERID("userId");

    private String value;

    ReservedClaims(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    public static ReservedClaims fromValue(String value) {

        if (value == null)
            return null;

        for (ReservedClaims type : ReservedClaims.values()) {

            if (type.value.equalsIgnoreCase(value))
                return type;
        }

        log.error("invalid Reserved claims : {}", value);
        return null;
    }
}
