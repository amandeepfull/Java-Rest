package com.commons.Dao;

import com.commons.entity.Token;

public interface TokenDao {


    Token createToken(String clientId, String userId);

    Token updateTokenForRefreshToken(String refreshToken);
}
