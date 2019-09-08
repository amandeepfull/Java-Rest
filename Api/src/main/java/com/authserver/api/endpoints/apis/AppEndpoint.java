package com.authserver.api.endpoints.apis;


import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.exception.EntityException;
import com.commons.DaoImplServices.AppDaoImpl;
import com.commons.Enum.EntityErrorCode;
import com.commons.entity.App;
import com.commons.exception.ForbiddenException;
import com.commons.response.ApiResponse;


import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/v1/app")
public class AppEndpoint extends AbstractBaseEndpoint {

    @POST
    @Path("/")
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
    public Response updateApp( App app, @PathParam("appId") String appId) throws EntityException {

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
    public Response getUserApp(@PathParam("userId") String userId) throws ForbiddenException {

        ApiResponse response = new ApiResponse();

        if(response == null)
            throw new ForbiddenException("you are not ldjlfjsdjf");

        List<App> apps = new ArrayList<>();

        apps = AppDaoImpl.getInstance().getUserApps(userId);

        response.setOk(true);
        response.add("apps", apps);
        return Response.ok(response).build();

    }
}
