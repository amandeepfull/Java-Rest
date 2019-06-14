package com.commons.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.extern.slf4j.Slf4j;
public enum ReservedClaims {

     ISSUED_TO("issuedTo"), SCOPES("scopes");

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

        System.out.println("invalid Reserved claims : {}"+ value);
        return null;
    }
}
