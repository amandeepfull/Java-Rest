package com.authserver.auth.endpoints;

import com.commons.Enum.EntityErrorCode;
import com.commons.annotations.ApiKeyCheck;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.entity.Contact;
import com.commons.entity.Token;
import com.commons.exception.EntityException;
import com.commons.exception.ForbiddenException;
import com.commons.requests.TokenRequest;
import com.commons.response.ApiResponse;
import com.commons.response.TokenResponse;
import com.commons.services.AuthenticationService;
import com.commons.services.FreeMarkerService;
import com.commons.utils.AppUtils;
import com.commons.utils.Preconditions;
import org.jose4j.jwt.MalformedClaimException;

import javax.annotation.Generated;
import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/o/auth")
public class AuthEndpoint extends AbstractBaseEndpoint {

    @POST
    @Path("/login")
    public Response doLogin(@QueryParam("redirect_uri") String redirectUri, @QueryParam("client_id") String clientId,
                            Contact contact, @QueryParam("state") String state) throws ServletException, IOException {

        return AuthenticationService.getInstance().login(redirectUri, clientId, state, contact, servletRequest, servletResponse);

    }


}
