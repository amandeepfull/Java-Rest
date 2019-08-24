package com.foody.auth.model;

import lombok.Data;

@Data
public class TokenResponse {

    private String accessToken;
    private String refreshToken;
    private long expiresAt;

    public TokenResponse(String accessToken, String refreshToken, long expiresAt){
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
    }
}
