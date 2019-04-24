package com.Default.endpoints.auth;

import com.commons.DaoImplServices.AppDaoImpl;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.entity.App;
import com.commons.entity.Contact;
import com.commons.objectify.OfyService;
import com.commons.response.ApiResponse;
import com.commons.services.AuthenticationService;
import com.commons.services.FreeMarkerService;
import com.commons.services.LoginSessionManager;
import com.commons.utils.AppUtils;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;
import com.googlecode.objectify.impl.EntityMemcacheStats;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/o/service")
public class ServiceEndpoint extends AbstractBaseEndpoint {

    @GET
    @Path("/login")
    public Response doLogin(@QueryParam("redirect_uri") String redirectUri, @QueryParam("client_id") String clientId,
                            @QueryParam("service") String service, @QueryParam("state") String state ){


        Preconditions.checkArgument(ObjUtil.isBlank(redirectUri) , "Invalid redirect url");

        App app = AppDaoImpl.getInstance().getById(clientId);

        Preconditions.checkArgument(app == null, "Invalid app");
        Preconditions.checkArgument(!app.getRedirectUri().equals(redirectUri), "pass only configured redirect uri");
        Preconditions.checkArgument(ObjUtil.isBlank(state), "Invalid state");

        if (LoginSessionManager.hasLoginSession(getSession())) {
            return AppUtils.getRedirectUriResponse(app.getHost()+"/"+service);
        }

        Map<String, Object> params = new HashMap<>();
        params.put("redirect_uri", redirectUri);
        params.put("clientId", clientId);
        params.put("state",state);

        FreeMarkerService.writeHtmlResponse(servletResponse, 200, "signin.html", params);
        return null;
    }


    @POST
    @Path("/signup")
    public Response doSignup(@QueryParam("continueUrl") String continueUrl, @QueryParam("clientId") String clientId, Contact contact){

        ApiResponse response = new ApiResponse();



        AuthenticationService.getInstance().signup(contact);

        response.setOk(true);
        response.add("contact", contact);
        return Response.ok(response).build();
    }
}
