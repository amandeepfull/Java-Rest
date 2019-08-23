package com.foody.order.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import lombok.Data;

@Entity
@Data
public class Order {

    @Id
    private String id;
    private String cartId;
    private String address;
    private Payment payment;
}
