package com.authserver.api.endpoints.apis;

import com.commons.Enum.EntityErrorCode;
import com.commons.annotations.ApiKeyCheck;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.entity.App;
import com.commons.exception.EntityException;
import com.commons.response.ApiResponse;
import com.commons.DaoImplServices.AppDaoImpl;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@ApiKeyCheck
@Path("v1/app")
public class AppEndpoint extends AbstractBaseEndpoint {

    @POST
    public Response registerApp(App app) throws EntityException {

        ApiResponse response = new ApiResponse();

        app = AppDaoImpl.getInstance().saveApp(app);

        if(app == null)
            throw new EntityException(EntityErrorCode.CREATE_FAILED, "failed to create app");

        response.add("app", app);
        response.setOk(true);

        return Response.ok(response).build();
    }
}
