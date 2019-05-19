package com.commons.entity;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import lombok.Data;

@Data
@Entity
public class Key extends AbstractBaseEntity{

    @Index
    private String privateKey;

    @Index
    private String publicKey;

    @Index
    private String clientId;

    @Index
    private String appId;

    public Key(String privateKey, String publicKey, String clientId, String appId){
        this.privateKey = privateKey;
        this.publicKey = publicKey;
        this.clientId = clientId;
        this.appId = appId;
    }
}
