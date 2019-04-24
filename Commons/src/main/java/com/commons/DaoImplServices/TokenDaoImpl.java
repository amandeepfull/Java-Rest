package com.commons.DaoImplServices;

import com.commons.Dao.TokenDao;
import com.commons.Enum.EntityErrorCode;
import com.commons.Enum.TokenType;
import com.commons.constants.CommonConstants;
import com.commons.entity.Contact;
import com.commons.entity.Token;
import com.commons.exception.EntityException;
import com.commons.objectify.OfyService;
import com.commons.services.JWTService;
import com.commons.utils.HashUtil;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;
import com.commons.utils.RandomUtil;

import javax.ws.rs.NotFoundException;

public class TokenDaoImpl extends OfyService implements TokenDao {

    private final long oneDayMilli = 86400000;

    @Override
    public Token createToken( String clientId, String userId) {

        Token token = new Token();

        String accessToken = JWTService.getInstance().createToken(clientId, userId, CommonConstants.USER_ACCESS_TOKEN_EXPIRY_MINS);

        if(accessToken == null)
            return null;

        token.setAccessToken(accessToken);
        token.setId(RandomUtil.generateRandomString(24));
        token.setRefreshToken(RandomUtil.generateRandomString(32));
        token.setExpiresAt(System.currentTimeMillis() + oneDayMilli * 7);
        token.setTokenType(TokenType.USER);
        token.setUserId(userId);
        token.setIssuedTo(clientId);
        token.setType("Bearer");

        return save(token) != null ? token : null;
    }

    @Override
    public Token updateTokenForRefreshToken(String refreshToken) {


       Token token =  getTokenByRefreshToken(refreshToken);
        if(token == null)
            throw new NotFoundException("refresh token not found");

        String userId = token.getUserId();
        Contact contact = ContactDaoImpl.getInstance().getById(userId);

        String acccessToken = JWTService.getInstance().createToken(contact.getClientId(), userId, CommonConstants.USER_ACCESS_TOKEN_EXPIRY_MINS);

        if(ObjUtil.isBlank(acccessToken))
            return null;

        token.setAccessToken(acccessToken);
        return save(token) != null ? token : null;
    }

    private Token getTokenByRefreshToken(String refreshToken) {
        Preconditions.checkArgument(ObjUtil.isBlank(refreshToken), "Invalid refresh token");

        return  OfyService.ofy().load().type(Token.class).filter("refreshToken", refreshToken).first().now();

    }

    private static class TokenDaoImplInitializer {
        private static final TokenDaoImpl tokenDaoImpl = new TokenDaoImpl();
    }

    public static TokenDaoImpl getInstance() {
        return TokenDaoImplInitializer.tokenDaoImpl;
    }
}
