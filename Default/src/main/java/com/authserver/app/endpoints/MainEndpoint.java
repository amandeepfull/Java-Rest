package com.authserver.app.endpoints;

import com.commons.DaoImplServices.ContactDaoImpl;
import com.commons.Enum.ReservedClaims;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.constants.CommonConstants;
import com.commons.entity.Contact;
import com.commons.http.HttpMethod;
import com.commons.http.HttpRequest;
import com.commons.http.HttpResponse;
import com.commons.http.UrlFetcher;
import com.commons.response.TokenResponse;
import com.commons.services.FreeMarkerService;
import com.commons.services.JWTService;
import com.commons.services.LoginSessionManager;
import com.commons.services.MCacheService;
import com.commons.utils.*;
import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.JwtClaims;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Path("/")
public class MainEndpoint extends AbstractBaseEndpoint {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response startApp() throws ServletException, IOException {

        return AppUtils.getRedirectUriResponse("/dashboard");
    }

    @GET
    @Path("/dashboard")
    @Produces(MediaType.TEXT_HTML)
    public Response getDashboard() throws IOException, ServletException {

        System.out.println("session created : " +LoginSessionManager.hasLoginSession(getSession()));
        if (!LoginSessionManager.hasLoginSession(getSession())) {
            return AppUtils.getRedirectUriResponse("/welcome");
        }


        FreeMarkerService.writeHtmlResponse(servletResponse, 200, AppUtils.getJspPagesPath("dashboard"), null);

        return null;
    }

    @GET
    @Path("/welcome")
    @Produces(MediaType.TEXT_HTML)
    public Response login() throws ServletException, IOException {

        System.out.println("session created : " +LoginSessionManager.hasLoginSession(getSession()));
        if (LoginSessionManager.hasLoginSession(getSession())) {
            return AppUtils.getRedirectUriResponse("/dashboard");
        }


        FreeMarkerService.writeHtmlResponse(servletResponse, 200, AppUtils.getJspPagesPath("index"), null);
        return null;
    }


    @GET
    @Path("/auth")
    @Produces(MediaType.TEXT_HTML)
    public Response handleAuth() {


        System.out.println("triggered auth : ");
        String stateId = RandomUtil.generateRandomString();
        MCacheService.getInstance().put(stateId, new Boolean(true), 60 * 60);

        System.out.println("stateId started : "+stateId);
        String host = Utils.getHost(servletRequest);
        String url = CommonConstants.OAUTH_CATER_URL+"/o/service/login?redirect_uri="+CommonConstants.OAUTH_CATER_AUTH_CALLBACK+"&client_id="+CommonConstants.OAUTH_CATER_CLIENT_ID+"&state=" + stateId+"&host="+host+"&service=dashboard";

        return AppUtils.getRedirectUriResponse(url);
    }

    @GET
    @Path("/auth/callback")
    @SuppressWarnings("unchecked")
    public Response handleOauthCallback(@QueryParam("auth_code") String authCode, @QueryParam("state") String state, @QueryParam("error") String error) throws IOException {


        System.out.println("state : "+state);
        System.out.println("auth code : "+authCode);
        Boolean isSameState = (Boolean) MCacheService.getInstance().get(state);
        if (isSameState == null) {
            return AppUtils.getRedirectUriResponse("/?error=invalid_request");
       }

        System.out.println("isSameState : "+isSameState);
        System.out.println("auth_code :" + authCode);

        Map<String, Object> payload = new HashMap<>();
        payload.put("auth_code", authCode);
        payload.put("client_id", CommonConstants.OAUTH_CATER_CLIENT_ID);
        payload.put("client_secret", CommonConstants.OAUTH_CATER_CLIENT_SECRET);
        payload.put("redirect_uri", CommonConstants.OAUTH_CATER_AUTH_CALLBACK);

        HttpRequest request = new HttpRequest(CommonConstants.OAUTH_CATER_URL+"/o/auth/token", HttpMethod.POST);
        request.setContentType("application/json");
        request.setPayload(ObjUtil.getJson(payload));
        HttpResponse response = UrlFetcher.makeRequest(request);

        System.out.println("sout : "+response.getResponseContent());
        Map<String, Object> respObj = ObjUtil.getMapFromJson(response.getResponseContent());
        Map<String, Object> tokenResp = (Map<String, Object>) respObj.get("data");
        Map<String, Object> token = (Map<String, Object>) tokenResp.get("token");


        String accessToken = (String) token.get("access_token");
        JwtClaims claims = JWTService.getInstance().decodeToken(accessToken);

        Contact contact = ContactDaoImpl.getInstance().getByIdFromRemote((String) claims.getClaimValue(ReservedClaims.USERID.toString()));

        System.out.println("Contact : "+contact);

        if(contact == null)
            return AppUtils.getRedirectUriResponse("/?error=login_failed");


        HttpSession session = LoginSessionManager.createUserSession(servletRequest, servletResponse, contact);
        session.setAttribute("token", accessToken);


        System.out.println("session created : " +LoginSessionManager.hasLoginSession(getSession()));


        return AppUtils.getRedirectUriResponse("/dashboard");

    }

}
