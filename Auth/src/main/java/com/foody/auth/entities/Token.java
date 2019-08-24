package com.foody.auth.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foody.auth.enums.TokenType;
import com.foody.auth.utils.HashUtil;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor

public class Token extends AbstractBaseEntity {


    private String hashedToken;

    @Index
    private String refreshToken;


    @Index
    private long expiresAt;


    @Index
    private String userName;


    public Token(String token, long expiresAt, String userName){
        this.id = UUID.randomUUID().toString();
        this.hashedToken = HashUtil.md5Hash(token);
        this.refreshToken = UUID.randomUUID().toString();
        this.expiresAt = System.currentTimeMillis() + (expiresAt * 1000);
        this.userName = userName;

    }

}

