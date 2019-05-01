package com.authserver.endpoints.app;

import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.utils.AppUtils;

import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/")
public class MainEndpoint extends AbstractBaseEndpoint {

    @GET
    public void startApp() throws ServletException, IOException {

       servletRequest.getRequestDispatcher(AppUtils.getJspPagesPath("index")).forward(servletRequest, servletResponse);
    }

}
