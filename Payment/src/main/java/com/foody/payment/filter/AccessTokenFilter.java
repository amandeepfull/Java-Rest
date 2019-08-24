package com.foody.payment.filter;

import com.foody.payment.response.ApiResponse;
import com.google.common.reflect.ClassPath;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AccessTokenFilter implements ContainerRequestFilter {

    @Context
    ClassPath.ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext reqContext) throws IOException {


        String token =  extractToken(reqContext.getHeaderString("Authorization"));

        checkForToken(token);

    }

    private void checkForToken(String token) {

    }


    public void abort(ContainerRequestContext reqContext, ApiResponse response) {

        Response resp = Response.status(Response.Status.UNAUTHORIZED)
                .header("WWW-Authenticate", "ApiKey realm=\"Auth API\"")
                .type("application/json")
                .entity(response)
                .build();
        reqContext.abortWith(resp);
    }

    public static String extractToken(String authHeader) {

            String[] part = authHeader.trim().split("=");
            return part.length == 2 ? part[1] : null;

    }
}
