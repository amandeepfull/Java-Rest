package com.authserver.auth.endpoints;

import com.commons.Enum.EntityErrorCode;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.entity.Contact;
import com.commons.entity.Token;
import com.commons.exception.EntityException;
import com.commons.exception.ForbiddenException;
import com.commons.requests.TokenRequest;
import com.commons.response.ApiResponse;
import com.commons.response.TokenResponse;
import com.commons.services.AuthenticationService;
import com.commons.utils.Preconditions;
import org.jose4j.jwt.MalformedClaimException;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;


@Path("/o/auth")
public class AuthEndpoint extends AbstractBaseEndpoint {

    @POST
    @Path("/login")
    public Response doLogin(@QueryParam("redirect_uri") String redirectUri, @QueryParam("client_id") String clientId,
                            Contact contact, @QueryParam("state") String state) {

        return AuthenticationService.getInstance().login(redirectUri, clientId, state, contact);

    }

    @POST
    @Path("/token")
    public Response createToken(TokenRequest tokenRequest) throws EntityException, ForbiddenException, MalformedClaimException

    {


        ApiResponse response = new ApiResponse();

        Preconditions.checkArgument(tokenRequest == null, "Invalid token request");

        Token token =  AuthenticationService.getInstance().createToken(tokenRequest.getCode(), tokenRequest.getClientId(), tokenRequest.getClientSecret(), tokenRequest.getRedirectUri());

        if(token == null)
            throw new EntityException(EntityErrorCode.CREATE_FAILED, "failed to create token");

        TokenResponse tokenResponse = new TokenResponse(token);
        response.add("token", tokenResponse);
        response.setOk(true);

        return Response.ok(response).build();
    }

    @POST
    @Path("/refresh/token")
    public Response updateTokenByRefreshToken(TokenRequest tokenRequest) throws EntityException, ForbiddenException, MalformedClaimException

    {
        ApiResponse response = new ApiResponse();

        Token token =  AuthenticationService.getInstance().updateToken(tokenRequest.getRefreshToken(), tokenRequest.getClientId(), tokenRequest.getClientSecret());

        if(token == null)
            throw new EntityException(EntityErrorCode.UPDATE_FAILED, "failed to update token by refresh token");

        TokenResponse tokenResponse = new TokenResponse(token);
        response.add("token", tokenResponse);
        response.setOk(true);

        return Response.ok(response).build();
    }



}
