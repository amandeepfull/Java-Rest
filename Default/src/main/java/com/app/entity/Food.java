package com.app.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import lombok.Data;

@Entity
@Data
public class Food {

    @Id
    private String id;

    @Index
    private String name;

    private float price;

    @Unindex
    private String hotelId;

}
