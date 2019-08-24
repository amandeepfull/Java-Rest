package com.foody.auth.services;

import com.foody.auth.baseEndpoints.objectify.OfyService;
import com.foody.auth.entities.Token;
import com.foody.auth.model.TokenResponse;
import com.foody.auth.utils.HashUtil;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;

public class TokenService extends OfyService {
    public TokenResponse createToken(String token) throws MalformedClaimException {


        JwtClaims claims = JWTService.getInstance().decodeToken(token);


        token = JWTService.getInstance().createUserAccessToken(claims.getSubject(), 60 * 7);

        Token authToken = new Token(token, 60 * 7, claims.getSubject());

        save(authToken);
        return new TokenResponse(token,  authToken.getRefreshToken(), authToken.getExpiresAt());

    }

    public TokenResponse refreshToken(String refreshToken) {

        Token token = OfyService.ofy().load().type(Token.class).filter("refreshToken", refreshToken).list().get(0);

       String accessToken =  JWTService.getInstance().createUserAccessToken(token.getUserName(), 60 * 7);

       token.setHashedToken(HashUtil.md5Hash(accessToken));
       token.setExpiresAt(System.currentTimeMillis() + (60 * 7 * 1000)) ;

       save(token);

       return new TokenResponse(accessToken, token.getRefreshToken(), token.getExpiresAt());
    }
}
