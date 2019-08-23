package com.authserver.app.baseEndpoints;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AbstractBaseEndpoint {

    @Context
    public HttpServletResponse servletResponse;

    @Context
   public HttpServletRequest servletRequest;

    @Context
    Request request;

    public HttpSession getSession() {
        return servletRequest.getSession(false);
    }

    protected Response badRequest(Object obj) {
        return Response.status(Response.Status.BAD_REQUEST).entity(obj).build();
    }
}
