package com.commons.response;

import com.commons.Enum.TokenType;
import com.commons.entity.Token;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TokenResponse {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("type")
    private String type;

    @JsonProperty("expires_in")
    private long expiresAt;

    @JsonProperty("scope")
    private List<String> scope;

    public TokenResponse(Token token) {
        this.accessToken = token.getAccessToken();
        this.refreshToken = token.getRefreshToken();
        this.type = token.getType();
        this.expiresAt = token.getExpiresAt();
        this.scope = token.getScopes();
    }
}
