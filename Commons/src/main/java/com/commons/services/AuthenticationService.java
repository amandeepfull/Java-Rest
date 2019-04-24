package com.commons.services;

import com.commons.DaoImplServices.AppDaoImpl;
import com.commons.DaoImplServices.ContactDaoImpl;
import com.commons.DaoImplServices.TokenDaoImpl;
import com.commons.Enum.ReservedClaims;
import com.commons.constants.CommonConstants;
import com.commons.entity.App;
import com.commons.entity.Contact;
import com.commons.objectify.OfyService;
import com.commons.entity.Token;
import com.commons.utils.AppUtils;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.core.Response;

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


        if (claims == null || !claims.getSubject().equals(clientId))
            throw new ForbiddenException("auth code expired/invalid");

        App app = AppDaoImpl.getInstance().getById(clientId);
        Preconditions.checkArgument(app == null || !app.getSecret().equals(clientSecret), "Invalid client Id/secret");
        Preconditions.checkArgument(!app.getRedirectUri().equals(redirectUri), "pass only configured redirect uri");
    }

    public Token getTokenByRefreshToken(String refreshToken) {

        Preconditions.checkArgument(ObjUtil.isBlank(refreshToken), "refresh token cannot be null/empty");

        return TokenDaoImpl.getInstance().updateTokenForRefreshToken(refreshToken);
    }

    private static class AuthenticationServiceInitializer {
        private static final AuthenticationService authenticationService = new AuthenticationService();

    }

    public Response login(String redirectUri, String clientId, String state, Contact contact) {


        Preconditions.checkArgument(ObjUtil.isBlank(redirectUri), "Invalid redirect url");

        App app = AppDaoImpl.getInstance().getById(clientId);

        Preconditions.checkArgument(app == null, "Invalid app");
        Preconditions.checkArgument(!app.getRedirectUri().equals(redirectUri), "Redirect URI is not matching with configured");

        Preconditions.checkArgument(contact == null, "Invalid credentials payload");

        Contact loginUser = ContactDaoImpl.getInstance().getByEmail(contact.getEmail());

        Preconditions.checkArgument(loginUser == null || (!loginUser.getPassword().equals(contact.getPassword())), "Invalid credentials");


        String authCode = JWTService.getInstance().createToken(clientId, loginUser.getId(), CommonConstants.AUTH_CODE_EXPIRE_MIN);

        MCacheService.getInstance().put(authCode, true);

        return AppUtils.getRedirectUriResponse(redirectUri + "?auth=" + authCode + "&state=" + state);
    }

    public static AuthenticationService getInstance() {
        return AuthenticationServiceInitializer.authenticationService;
    }

    public Contact signup(Contact contact) {

        return ContactDaoImpl.getInstance().createContact(contact);
    }
}
