package com.commons.entity;

import com.commons.Enum.AppType;
import com.commons.utils.ObjUtil;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Serialize;
import com.googlecode.objectify.annotation.Unindex;
import lombok.Data;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    @Unindex
    private Set<String> redirectUri;

    @Unindex
    private Set<String> scopes;

    @Index
    private AppType type;

    @Unindex
    private String service;

    @Unindex
    @Serialize
    private Map<String, Object> meta;


    public App merge(App updateApp) {


        if(!ObjUtil.isBlank(updateApp.website))
        website = updateApp.website;

        if(!ObjUtil.isBlank(updateApp.logo))
        logo = updateApp.logo;

        if(!ObjUtil.isBlank(updateApp.service))
            service = updateApp.service;

        if(!ObjUtil.isBlank(updateApp.name))
            name = updateApp.name;

        if(!ObjUtil.isNullOrEmpty(updateApp.redirectUri))
            redirectUri.addAll(updateApp.redirectUri);

        return this;
    }
}
