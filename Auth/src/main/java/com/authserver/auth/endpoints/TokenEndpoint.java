package com.authserver.auth.endpoints;

import com.authserver.auth.services.TokenDaoImpl;
import com.commons.Enum.EntityErrorCode;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.entity.Token;
import com.commons.exception.EntityException;
import com.commons.exception.ForbiddenException;
import com.commons.requests.TokenRequest;
import com.commons.response.ApiResponse;
import com.commons.response.TokenResponse;
import com.authserver.auth.services.AuthenticationService;
import com.commons.utils.Preconditions;
import org.jose4j.jwt.MalformedClaimException;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("/o/token")
public class TokenEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response createToken(TokenRequest tokenRequest) throws EntityException, ForbiddenException, MalformedClaimException

    {
        ApiResponse response = new ApiResponse();

        Preconditions.checkArgument(tokenRequest == null, "Invalid token request");

        Token token =  AuthenticationService.getInstance().createToken(tokenRequest.getCode(), tokenRequest.getClientId(), tokenRequest.getClientSecret(), tokenRequest.getRedirectUri(), tokenRequest.getScopes());

        if(token == null)
            throw new EntityException(EntityErrorCode.CREATE_FAILED, "failed to create token");

        TokenResponse tokenResponse = new TokenResponse(token);
        response.add("token", tokenResponse);
        response.setOk(true);

        return Response.ok(response).build();
    }

    @PUT
    public Response updateTokenByRefreshToken(TokenRequest tokenRequest) throws EntityException

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

    @GET
    @Path("/{accessToken}")
    public Response getTokenByAccessToken(@PathParam("accessToken") String accessToken)

    {
        ApiResponse response = new ApiResponse();

        Token token =  TokenDaoImpl.getInstance().getByToken(accessToken);

        if(token == null)
            throw new NotFoundException("Token not found");

        response.add("token", token);
        response.setOk(true);

        return Response.ok(response).build();
    }


}
