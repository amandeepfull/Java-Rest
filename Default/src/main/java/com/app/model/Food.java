package com.app.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Food {

    @Id
    private String id;

    @Index
    private String name;

    @Unindex
    private String hotelId;

}
