package com.commons.filters;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/* Applies to all response */
@Priority(Priorities.HEADER_DECORATOR)
@Provider
public class CommonApiResponseFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext reqContext, ContainerResponseContext respContext) throws IOException {

//        respContext.getHeaders().add("X-Frame-Options", "SAMEORIGIN");
//        respContext.getHeaders().add("X-Content-Type-Options", "nosniff");
//        respContext.getHeaders().add("X-XSS-Protection", "1; mode=block");
//
//        // force https only
//        respContext.getHeaders().add("Strict-Transport-Security", "max-age=31536000");
//
//        respContext.getHeaders().add("Cache-Control", "private, no-cache, max-age=0, must-revalidate");
//        respContext.getHeaders().add("Pragma", "no-cache");

        respContext.getHeaders().add("Access-Control-Allow-Methods","POST,GET,OPTIONS,DELETE,PUT");
        respContext.getHeaders().add("Access-Control-Max-Age","3600");
        respContext.getHeaders().add("Access-Control-Allow-Headers","x-requested-with, Authorization,Content-Type");
        respContext.getHeaders().add("Access-Control-Allow-Origin","*");

        if(reqContext.getMethod().equals("OPTIONS"))
            respContext.setStatus(HttpServletResponse.SC_OK);

    }

}
