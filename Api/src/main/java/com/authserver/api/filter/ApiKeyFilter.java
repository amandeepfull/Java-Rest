package com.authserver.api.filter;

import com.authserver.api.constants.ApiConstants;
import com.commons.Enum.ApiErrorCode;
import com.commons.response.ApiResponse;
import com.commons.utils.ObjUtil;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(1)
public class ApiKeyFilter implements Filter {
    @Override
    public void destroy() {}

    @Override
    public void doFilter
            (ServletRequest request, ServletResponse response, FilterChain filterchain) throws IOException, ServletException {
                HttpServletRequest servletRequest = (HttpServletRequest) request;
                 String apiKey =  extractApiKey(servletRequest.getHeader("Authorization"));

                 if (ObjUtil.isBlank(apiKey) || !apiKey.equals(ApiConstants.AUTH_API_KEY)){
                     ApiResponse apiResp = new ApiResponse(false, ApiErrorCode.UNAUTHORIZED_REQUEST, "Invalid api key");
                     abort((HttpServletResponse)response, apiResp);
                     return;
                 }

                 filterchain.doFilter(request, response);
             }

    private void abort(HttpServletResponse servletResponse, ApiResponse apiResp) throws IOException {

        servletResponse.setHeader("Content-Type", "application/json");
        servletResponse.setStatus(401);
        servletResponse.getOutputStream().write(ObjUtil.getJson(apiResp).getBytes());
    }

    @Override
    public void init(FilterConfig filterconfig) throws ServletException {}

    public static String extractApiKey(String authHeader) {
        if (!ObjUtil.isBlank(authHeader)) {
            String[] part = authHeader.trim().split("=");
            return part.length == 2 ? part[1] : null;
        } else {
            return null;
        }
    }


}

