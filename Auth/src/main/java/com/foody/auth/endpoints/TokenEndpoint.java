package com.foody.auth.endpoints;

import com.foody.auth.baseEndpoints.AbstractBaseEndpoint;
import com.foody.auth.entities.Token;
import com.foody.auth.model.TokenRequestModel;
import com.foody.auth.model.TokenResponse;
import com.foody.auth.response.ApiResponse;
import com.foody.auth.services.JWTService;
import com.foody.auth.services.TokenService;
import com.google.appengine.repackaged.com.google.api.client.auth.oauth2.TokenRequest;
import com.google.appengine.repackaged.com.google.protobuf.Api;
import org.jose4j.jwt.MalformedClaimException;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/o/token")
public class TokenEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response createToken(TokenRequestModel request) throws MalformedClaimException {

        ApiResponse response = new ApiResponse();

        TokenResponse tokenResponse =  new TokenService().createToken(request.getAuthCode());

        response.add("token", tokenResponse);
        response.setOk(true);

        return Response.ok(response).build();
    }

    @PUT
    @Path("/refresh/{refreshToken}")
    public Response refreshToken(@PathParam("refreshToken") String refreshToken){

        ApiResponse response = new ApiResponse();

       TokenResponse tokenResponse =  new TokenService().refreshToken(refreshToken);

        response.add("token", tokenResponse);
        response.setOk(true);

        return Response.ok(response).build();
    }


}
