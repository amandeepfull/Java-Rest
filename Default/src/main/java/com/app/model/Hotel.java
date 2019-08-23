package com.app.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import lombok.Data;

@Data
@Entity
public class Hotel {

    @Id
    private String id;

    @Index
    private String name;

    private String location;

}
