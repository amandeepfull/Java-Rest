package com.app.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import lombok.Data;

@Entity
@Data
public class Food extends AbstractBaseEntity{


    @Index
    private String name;

    @Unindex
    private double price;

    @Unindex
    private String hotelId;

}
