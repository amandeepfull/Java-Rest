package com.commons.entity;

import com.commons.Enum.TokenType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Ignore;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Unindex;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class Token  extends AbstractBaseEntity {


    @Index
    private String accessToken;

    @Index
    private String refreshToken;

    @Unindex
    private String type;

    @Index
    private long expiresAt;

    @Unindex
    private List<String> scopes;

    @Index
    private String userName;

    @Index
    private String issuedTo;

    @Index
    private TokenType tokenType;

    @Ignore
    @JsonIgnore
    private String nonHashedToken;

}
