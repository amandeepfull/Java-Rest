package com.authserver.auth.endpoints;

import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.services.AuthenticationService;

import javax.servlet.ServletException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/o/auth")
public class AuthEndpoint extends AbstractBaseEndpoint {

    @POST
    @Path("/login")
    public Response doLogin(@QueryParam("redirect_uri") String redirectUri, @QueryParam("client_id") String clientId,
                            Contact contact, @QueryParam("state") String state) throws ServletException, IOException {

        return AuthenticationService.getInstance().login(redirectUri, clientId, state, contact, servletRequest, servletResponse);

    }


}
