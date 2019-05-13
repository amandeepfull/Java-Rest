package com.commons.DaoImplServices;

import com.commons.Dao.TokenDao;
import com.commons.Enum.TokenType;
import com.commons.constants.CommonConstants;
import com.commons.entity.Contact;
import com.commons.entity.Token;
import com.commons.http.HttpMethod;
import com.commons.http.HttpRequest;
import com.commons.http.HttpResponse;
import com.commons.http.UrlFetcher;
import com.commons.objectify.OfyService;
import com.commons.services.JWTService;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;
import com.commons.utils.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.NotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class TokenDaoImpl extends OfyService implements TokenDao {

    private final long oneDayMilli = 86400000;

    @Override
    public Token createToken( String clientId, String userName, List<String> scopes) {



        Token token = JWTService.getInstance().createUserAccessToken(clientId, userName, scopes, CommonConstants.USER_ACCESS_TOKEN_EXPIRY_MINS);

        if(token == null || ObjUtil.isBlank(token.getAccessToken()) || ObjUtil.isBlank(token.getPrivateKey()))
            return null;

        token.setId(RandomUtil.generateRandomString(24));
        token.setRefreshToken(RandomUtil.generateRandomString(32));
        token.setExpiresAt(System.currentTimeMillis() + oneDayMilli * 7);
        token.setTokenType(TokenType.USER);
        token.setUserName(userName);
        token.setIssuedTo(clientId);
        token.setType("Bearer");
        token.setScopes(scopes);

        return save(token) != null ? token : null;
    }

    @Override
    public Token updateUserTokenForRefreshToken(String refreshToken) {


       Token token =  getByRefreshToken(refreshToken);
        if(token == null)
            throw new NotFoundException("refresh token not found");


         Token updatedTokenWithKey = JWTService.getInstance().createUserAccessToken(token.getIssuedTo(), token.getUserName(), token.getScopes(), CommonConstants.USER_ACCESS_TOKEN_EXPIRY_MINS);

        if(updatedTokenWithKey == null || ObjUtil.isBlank(updatedTokenWithKey.getAccessToken()) || ObjUtil.isBlank(updatedTokenWithKey.getPrivateKey()))
            return null;

        token.setAccessToken(updatedTokenWithKey.getAccessToken());
        token.setPrivateKey(updatedTokenWithKey.getPrivateKey());

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
        return OfyService.ofy().load().type(Token.class).filter("accessToken", token).first().now();

    }


    //// todo needs to put in sdk
    public Token getByTokenFromRemote(String accessToken) {

        Preconditions.checkArgument(ObjUtil.isBlank(accessToken), "access token cannot be null/empty");

        try {
            HttpRequest request = new HttpRequest(CommonConstants.OAUTH_CATER_AUTH_URL + "/o/token/"+accessToken , HttpMethod.GET);

            //request.addHeader("Authorization", "ApiKey=" + CommonConstants.AUTH_API_KEY);

            HttpResponse response = UrlFetcher.makeRequest(request);
            if (!response.wasSuccessful()) {
                log.error("error response : " + response.getResponseContent());
                return null;
            }

            Map<String, Object> apiResponse = ObjUtil.getMapFromJson(response.getResponseContent());

            return ObjUtil.safeConvertMap((Map<String, Object>) ((Map<String, Object>) apiResponse.get("data")).get("token"), Token.class);
        } catch (Exception e) {
            log.error("exception while fetching contact by Id from remote : ", e.getMessage(), e);
            return null;
        }

    }


    private static class TokenDaoImplInitializer {
        private static final TokenDaoImpl tokenDaoImpl = new TokenDaoImpl();
    }

    public static TokenDaoImpl getInstance() {
        return TokenDaoImplInitializer.tokenDaoImpl;
    }
}
