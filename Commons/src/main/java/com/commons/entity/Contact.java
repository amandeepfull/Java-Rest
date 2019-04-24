package com.commons.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import lombok.Data;

@Entity
@Data
public class Contact extends AbstractBaseEntity {


    private String firstName;

    private String lastName;

    @Index
    private String email;

    private String password;

    @Index
    private String clientId;

}
