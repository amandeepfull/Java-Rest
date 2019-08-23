package com.foody.payment.entities;

import com.foody.payment.enums.PaymentStatus;
import com.foody.payment.enums.PaymentType;
import com.googlecode.objectify.annotation.Entity;
import lombok.Data;

@Entity
@Data
public class Payment extends AbstractBaseEntity {

    private String orderId;
    private String userId;
    private PaymentStatus status;
    private PaymentType type;

}
