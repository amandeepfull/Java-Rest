package com.commons.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenRequest {

    @JsonProperty("auth_code")
    private String code;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("redirect_uri")
    private String redirectUri;

    @JsonProperty("refresh_token")
    private String refreshToken;
}
