package com.foody.auth.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import lombok.Data;

@Entity
@Data
public class Login extends AbstractBaseEntity {

    @Index
    private String email;

    private String password;

}
