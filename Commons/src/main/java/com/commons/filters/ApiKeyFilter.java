package com.commons.filters;
import com.commons.Enum.ApiErrorCode;
import com.commons.annotations.ApiKeyCheck;
import com.commons.constants.CommonConstants;
import com.commons.response.ApiResponse;
import com.commons.utils.ObjUtil;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;


@Slf4j
@Provider
@ApiKeyCheck
public class ApiKeyFilter implements ContainerRequestFilter {

    @Context
    ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext reqContext) throws IOException {

        String apiKey =  extractApiKey(reqContext.getHeaderString("Authorization"));

        if (ObjUtil.isBlank(apiKey) || !apiKey.equals(CommonConstants.AUTH_API_KEY)){
            ApiResponse response = new ApiResponse(false, ApiErrorCode.UNAUTHORIZED_REQUEST, "Invalid api key");
            abort(reqContext, response);
            return;
        }

    }



    public void abort(ContainerRequestContext reqContext, ApiResponse response) {

        Response resp = Response.status(Response.Status.UNAUTHORIZED)
                .header("WWW-Authenticate", "ApiKey realm=\"Auth API\"")
                .type(CommonConstants.JSON_CONTENTTYPE)
                .entity(response)
                .build();
        reqContext.abortWith(resp);
    }

    public static String extractApiKey(String authHeader) {
        if (!ObjUtil.isBlank(authHeader)) {
            String[] part = authHeader.trim().split("=");
            return part.length == 2 ? part[1] : null;
        } else {
            return null;
        }
    }
}
