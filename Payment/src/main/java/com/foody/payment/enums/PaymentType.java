package com.foody.payment.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum PaymentType {
    COD, CREDIT_CARD, NET_BANKING;

    @JsonValue
    @Override
    public String toString() {
        return name().toLowerCase();
    }

    @JsonCreator
    public static PaymentType
    fromValue(String value) {
        try {
            return PaymentType.valueOf(value.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }
}

