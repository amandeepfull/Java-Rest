package com.commons.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AppType {

    MOBILE, WEB, WEB_MOB;

    @JsonValue
    @Override
    public String toString() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static AppType fromValue(String value) {
        try {
            return AppType.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }


}


