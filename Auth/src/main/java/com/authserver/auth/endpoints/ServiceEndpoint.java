package com.authserver.auth.endpoints;


import com.commons.DaoImplServices.AppDaoImpl;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.entity.App;
import com.commons.services.FreeMarkerService;
import com.commons.utils.AppUtils;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;

import javax.ws.rs.GET;
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
                            @QueryParam("service") String service, @QueryParam("state") String state, @QueryParam("host") String host ){


        Preconditions.checkArgument(ObjUtil.isBlank(redirectUri) , "Invalid redirect url");

        App app = AppDaoImpl.getInstance().getByClientId(clientId);

        Preconditions.checkArgument(app == null, "Invalid app");
        Preconditions.checkArgument(!app.getRedirectUri().contains(redirectUri), "pass only configured redirect uri");
        Preconditions.checkArgument(ObjUtil.isBlank(state), "Invalid state");
       // Preconditions.checkArgument(ObjUtil.isBlank(service) || !app.getService().equals(service), "Invalid service");

        Map<String, Object> params = new HashMap<>();
        params.put("redirect_uri", redirectUri);
        params.put("client_id", clientId);
        params.put("state",state);
        params.put("host", host);
        params.put("service",service);

        FreeMarkerService.writeHtmlResponse(servletResponse, 200, AppUtils.getHtmlPath("login"), params);
        return null;
    }


}
