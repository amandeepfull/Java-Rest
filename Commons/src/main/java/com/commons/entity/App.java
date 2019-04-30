package com.commons.entity;

import com.commons.Enum.AppType;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Serialize;
import com.googlecode.objectify.annotation.Unindex;
import lombok.Data;

import java.util.Map;

@Data
@Entity
public class App extends AbstractBaseEntity {

    @Index
    private String name;

    @Unindex
    private String logo;

    @Unindex
    private String website;

    @Index
    private String clientId;

    @Unindex
    private String clientSecret;

    @Index
    private String redirectUri;

    @Unindex
    private String host;

    @Index
    private AppType type;


    @Unindex
    @Serialize
    private Map<String, Object> meta;
}
