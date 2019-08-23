package com.foody.order.entities;

import com.foody.order.enums.PaymentType;
import com.googlecode.objectify.annotation.Entity;
import lombok.Data;

@Entity
@Data
public class Order extends AbstractBaseEntity{

    private String cartId;
    private String address;
    private double totalAmt;
    private PaymentType paymentType;
}
