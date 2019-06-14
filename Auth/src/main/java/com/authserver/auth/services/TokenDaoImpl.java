package com.authserver.auth.services;

import com.authserver.auth.constants.AuthConstants;
import com.commons.Dao.TokenDao;
import com.commons.Enum.TokenType;
import com.commons.entity.Token;
import com.commons.objectify.OfyService;
import com.commons.services.JWTService;
import com.commons.utils.HashUtil;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;
import com.commons.utils.RandomUtil;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class TokenDaoImpl extends OfyService implements TokenDao {

    private final long oneDayMilli = 86400000;

    @Override
    public Token createToken( String clientId, String userName, List<String> scopes) {



       String accessToken = JWTService.getInstance().createUserAccessToken(clientId, userName, scopes, AuthConstants.USER_ACCESS_TOKEN_EXPIRY_MINS);

        if(accessToken == null )
            return null;

        Token token = new Token();
        token.setId(RandomUtil.generateRandomString(24));
        token.setRefreshToken(RandomUtil.generateRandomString(32));
        token.setExpiresAt(System.currentTimeMillis() + oneDayMilli * 7);
        token.setTokenType(TokenType.USER);
        token.setUserName(userName);
        token.setIssuedTo(clientId);
        token.setType("Bearer");
        token.setScopes(scopes);
        token.setAccessToken(HashUtil.sha256(accessToken));
        token.setNonHashedToken(accessToken);
        return save(token) != null ? token : null;
    }

    @Override
    public Token updateUserTokenForRefreshToken(String refreshToken) {


       Token token =  getByRefreshToken(refreshToken);
        if(token == null)
            throw new NotFoundException("refresh token not found");


         String accessToken = JWTService.getInstance().createUserAccessToken(token.getIssuedTo(), token.getUserName(), token.getScopes(), AuthConstants.USER_ACCESS_TOKEN_EXPIRY_MINS);

        if(accessToken == null)
            return null;

        token.setAccessToken(HashUtil.sha256(accessToken));
        token.setNonHashedToken(accessToken);
        return save(token) != null ? token : null;
    }


    @Override
    public Token getByRefreshToken(String refreshToken) {
        Preconditions.checkArgument(ObjUtil.isBlank(refreshToken), "Refresh token cannot be null/empty");
        return OfyService.ofy().load().type(Token.class).filter("refreshToken", refreshToken).first().now();
    }

    @Override
    public Token getByToken(String token) {
        Preconditions.checkArgument(ObjUtil.isBlank(token), "access token cannot be null/empty");
        return OfyService.ofy().load().type(Token.class).filter("accessToken", HashUtil.sha256(token)).first().now();

    }


    //// todo needs to put in sdk



    private static class TokenDaoImplInitializer {
        private static final TokenDaoImpl tokenDaoImpl = new TokenDaoImpl();
    }

    public static TokenDaoImpl getInstance() {
        return TokenDaoImplInitializer.tokenDaoImpl;
    }
}
