package com.commons.services;

import com.commons.Dao.TokenDao;
import com.commons.DaoImplServices.AppDaoImpl;
import com.commons.DaoImplServices.ContactDaoImpl;
import com.commons.DaoImplServices.TokenDaoImpl;
import com.commons.Enum.ReservedClaims;
import com.commons.constants.CommonConstants;
import com.commons.entity.App;
import com.commons.entity.Contact;
import com.commons.http.HttpMethod;
import com.commons.http.HttpRequest;
import com.commons.http.UrlFetcher;
import com.commons.objectify.OfyService;
import com.commons.entity.Token;
import com.commons.requests.TokenRequest;
import com.commons.utils.AppUtils;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;
import com.commons.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class AuthenticationService extends OfyService {


    public Token createToken(String authCode, String clientId, String clientSecret, String redirectUri) throws ForbiddenException, MalformedClaimException {


        Preconditions.checkArgument(ObjUtil.isBlank(authCode), "auth code cannot be null/empty");
        Preconditions.checkArgument(ObjUtil.isBlank(clientId), "client Id cannot be null/empty");

        if(MCacheService.getInstance().get(authCode) == null)
            throw new ForbiddenException("auth code expired/invalid");

        JwtClaims claims = JWTService.getInstance().decodeToken(authCode);

        validateAuthCodeClaims(claims, clientId, clientSecret, redirectUri);

        Contact contact = ContactDaoImpl.getInstance().getById((String) claims.getClaimValue(ReservedClaims.USERID.toString()));

        Token token = TokenDaoImpl.getInstance().createToken(clientId, contact.getId());

        if(token != null)
        MCacheService.getInstance().remove(authCode);

        return token;

    }

    private void validateAuthCodeClaims(JwtClaims claims, String clientId, String clientSecret, String redirectUri) throws MalformedClaimException {


        System.out.println("claims : "+claims.toString());
        System.out.println("clienId : "+clientId);
        if (claims == null || !claims.getSubject().equals(clientId))
            throw new ForbiddenException("auth code expired/invalid");

        App app = AppDaoImpl.getInstance().getByClientId(clientId);
        Preconditions.checkArgument(app == null || !app.getClientSecret().equals(clientSecret), "Invalid client Id/secret");
        Preconditions.checkArgument(!app.getRedirectUri().contains(redirectUri), "pass only configured redirect uri");
    }

    public Token updateToken(String refreshToken, String clientId, String clientSecret) {

        validateUpdateToken(refreshToken, clientId, clientSecret);

        return TokenDaoImpl.getInstance().updateTokenForRefreshToken(refreshToken);
    }

    private void validateUpdateToken(String refreshToken, String clientId, String clientSecret) {

        Preconditions.checkArgument(ObjUtil.isBlank(refreshToken), "refresh token cannot be null/empty");

        Token token = TokenDaoImpl.getInstance().getByRefreshToken(refreshToken);

        if(token == null)
            throw new NotFoundException("refresh token not found");

        App app = AppDaoImpl.getInstance().getById(token.getIssuedTo());

        if(app == null)
            throw new NotFoundException("client not found");

        Preconditions.checkArgument(!app.getId().equals(clientId) || !app.getClientSecret().equals(clientSecret), "Invalid clientId/secret to update access token with refresh token");

    }

    private static class AuthenticationServiceInitializer {
        private static final AuthenticationService authenticationService = new AuthenticationService();

    }

    public Response login(String redirectUri, String clientId, String state, Contact contact, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException {


        Preconditions.checkArgument(ObjUtil.isBlank(redirectUri), "Invalid redirect url");

        App app = AppDaoImpl.getInstance().getByClientId(clientId);

        Preconditions.checkArgument(app == null, "Invalid app");
        Preconditions.checkArgument(!app.getRedirectUri().contains(redirectUri), "Redirect URI is not matching with configured");

        Preconditions.checkArgument(contact == null, "Invalid credentials payload");

        Contact loginUser = ContactDaoImpl.getInstance().getByEmail(contact.getEmail());

        Preconditions.checkArgument(loginUser == null || (!loginUser.getPassword().equals(contact.getPassword())), "Invalid credentials");


        String authCode = JWTService.getInstance().createToken(clientId, loginUser.getId(), CommonConstants.AUTH_CODE_EXPIRE_MIN);

        MCacheService.getInstance().put(authCode, true, 60);

        System.out.println("Contact : "+ObjUtil.getJson(contact));

        String url = redirectUri+"?auth_code="+authCode+"&state="+state;

        System.out.println("redirected to : "+url);

        Map<String, String> params = new HashMap<>();
        params.put("auth_code", authCode);
        params.put("state", state);
        params.put("redirect_uri",redirectUri);

        FreeMarkerService.writeHtmlResponse(servletResponse, 200, AppUtils.getHtmlPath("redirect"), params);
        return null;

    }

    public static AuthenticationService getInstance() {
        return AuthenticationServiceInitializer.authenticationService;
    }


}
