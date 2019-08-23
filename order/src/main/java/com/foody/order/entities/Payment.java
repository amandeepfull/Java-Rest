package com.foody.order.entities;

import com.foody.order.PaymentType;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Data;

@Data
@Entity
public class Payment {

    @Id
    private String id;
    private PaymentType type;
}
