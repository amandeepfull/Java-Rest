package com.commons.Dao;

import com.commons.entity.Token;

import java.util.List;

public interface TokenDao {


    Token createToken(String clientId, String userId, List<String> scopes);

    Token updateUserTokenForRefreshToken(String refreshToken);

    Token getByRefreshToken(String refreshToken);

    Token getByToken(String token);
}
