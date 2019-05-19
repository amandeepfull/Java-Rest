package com.authserver.api.endpoints.apis;

import com.commons.Enum.EntityErrorCode;
import com.commons.annotations.ApiKeyCheck;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.entity.App;
import com.commons.exception.EntityException;
import com.commons.response.ApiResponse;
import com.commons.DaoImplServices.AppDaoImpl;
import com.google.appengine.repackaged.com.google.protobuf.Api;
import org.jose4j.http.Get;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@ApiKeyCheck
@Path("v1/app")
public class AppEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response registerApp(App app) throws EntityException {

        ApiResponse response = new ApiResponse();

        app = AppDaoImpl.getInstance().create(app);

        if (app == null)
            throw new EntityException(EntityErrorCode.CREATE_FAILED, "failed to create app");

        response.add("app", app);
        response.setOk(true);

        return Response.ok(response).build();
    }


    @PUT
    @Path("/{appId}")
    public Response updateApp(App app, @PathParam("appId") String appId) throws EntityException {

        ApiResponse response = new ApiResponse();

        app = AppDaoImpl.getInstance().updateApp(appId, app);

        if (app == null)
            throw new EntityException(EntityErrorCode.UPDATE_FAILED, "failed to update app Info");


        response.add("app", app);
        response.setOk(true);

        return Response.ok(response).build();
    }

    @GET
    @Path("/user/{userId}")
    public Response getUserApp(@PathParam("userId") String userId) {

        ApiResponse response = new ApiResponse();

        List<App> apps = new ArrayList<>();

        apps = AppDaoImpl.getInstance().getUserApps(userId);

        response.setOk(true);
        response.add("apps", apps);
        return Response.ok(response).build();

    }
}
