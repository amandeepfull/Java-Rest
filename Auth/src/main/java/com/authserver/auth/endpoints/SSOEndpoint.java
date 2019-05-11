package com.authserver.auth.endpoints;

import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.services.FreeMarkerService;
import com.commons.services.MCacheService;
import com.commons.utils.AppUtils;
import com.commons.utils.HashUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/o/sso")
public class SSOEndpoint extends AbstractBaseEndpoint {

    @GET
    @Path("/CheckCookie")
    public Response checkCookie(@QueryParam("state") String state, @QueryParam("redirect_uri") String redirectUri){

        String authCode = (String) MCacheService.getInstance().get(HashUtil.sha256(state + redirectUri));

        Map<String, String> params = new HashMap<>();
        params.put("auth_code", authCode);
        params.put("state", state);
        params.put("redirect_uri",redirectUri);

        FreeMarkerService.writeHtmlResponse(servletResponse, 200, AppUtils.getHtmlPath("redirect"), params);

        return null;
    }
}
