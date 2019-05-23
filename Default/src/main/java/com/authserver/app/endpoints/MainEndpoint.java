package com.authserver.app.endpoints;

import com.commons.DaoImplServices.ContactDaoImpl;
import com.commons.DaoImplServices.TokenDaoImpl;
import com.commons.Enum.OauthCaterScopes;
import com.commons.baseEndpoints.AbstractBaseEndpoint;
import com.commons.constants.CommonConstants;
import com.commons.entity.Contact;
import com.commons.entity.Token;
import com.commons.http.HttpMethod;
import com.commons.http.HttpRequest;
import com.commons.http.HttpResponse;
import com.commons.http.UrlFetcher;
import com.commons.services.FreeMarkerService;
import com.commons.services.JWTService;
import com.commons.services.LoginSessionManager;
import com.commons.services.MCacheService;
import com.commons.utils.AppUtils;
import com.commons.utils.ObjUtil;
import com.commons.utils.RandomUtil;
import com.commons.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    public Response getDashboard(@QueryParam("error") String error) throws IOException, ServletException {

        if (!LoginSessionManager.hasLoginSession(getSession())) {
            return AppUtils.getRedirectUriResponse("/welcome");
        }



        servletRequest.getRequestDispatcher(AppUtils.getJspPagesPath("dashboard")).forward(servletRequest, servletResponse);
        return null;
    }

    @GET
    @Path("/welcome")
    @Produces(MediaType.TEXT_HTML)
    public Response login() throws ServletException, IOException {

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


        String stateId = RandomUtil.generateRandomString();
        MCacheService.getInstance().put(stateId, new Boolean(true), 60 * 60);


        /// TODO needed for login service
        String host = Utils.getHost(servletRequest);
        String url = CommonConstants.OAUTH_CATER_AUTH_URL+"/o/service/login?redirect_uri="+CommonConstants.OAUTH_CATER_AUTH_CALLBACK+"&client_id="+CommonConstants.OAUTH_CATER_CLIENT_ID+"&state=" + stateId+"&service=dashboard";


        ////////// SDK needed ................................................................

        return AppUtils.getRedirectUriResponse(url);
    }

    @GET
    @Path("/auth/callback")
    @SuppressWarnings("unchecked")
    public Response handleOauthCallback(@QueryParam("auth_code") String authCode, @QueryParam("state") String state, @QueryParam("error") String error) throws IOException, MalformedClaimException {

        Boolean isSameState = (Boolean) MCacheService.getInstance().get(state);
        if (isSameState == null) {
            return AppUtils.getRedirectUriResponse("/welcome?error=invalid_request");
       }



        /// Todo SDK process ...........................................................................


        /// TODO SDK is needed for full process
        ///  Todo sdk would be helpfull to store private key in first fetch of token from auth server and decode.
        // TODO for now we will fetch token from db and do, just for testing purpose

        Map<String, Object> payload = new HashMap<>();
        payload.put("auth_code", authCode);
        payload.put("client_id", CommonConstants.OAUTH_CATER_CLIENT_ID);
        payload.put("client_secret", CommonConstants.OAUTH_CATER_CLIENT_SECRET);
        payload.put("redirect_uri", CommonConstants.OAUTH_CATER_AUTH_CALLBACK);

        Set<String> scopes = new HashSet<>();
        scopes.add(OauthCaterScopes.FULL_ACCESS.toString());
        payload.put("scopes", scopes);

        HttpRequest request = new HttpRequest(CommonConstants.OAUTH_CATER_AUTH_URL+"/o/token", HttpMethod.POST);
        request.addHeader("Authorization", "ApiKey="+CommonConstants.AUTH_API_KEY);
        request.setContentType("application/json");
        request.setPayload(ObjUtil.getJson(payload));
        HttpResponse response = UrlFetcher.makeRequest(request);

        System.out.println("sout : "+response.getResponseContent());
        Map<String, Object> respObj = ObjUtil.getMapFromJson(response.getResponseContent());
        Map<String, Object> tokenResp = (Map<String, Object>) respObj.get("data");
        Map<String, Object> token = (Map<String, Object>) tokenResp.get("token");


        String accessToken = (String) token.get("access_token");


        JwtClaims claims = JWTService.getInstance().decodeToken(accessToken);

////////// ........................................  SDK process .....................................


        // may be from our db or any contact management app, it is upto choice registered app
        Contact contact = ContactDaoImpl.getInstance().getByUnameFromRemote(claims.getSubject());


        if(contact == null)
            return AppUtils.getRedirectUriResponse("/welcome?error=invalid_user");


        HttpSession session = LoginSessionManager.createUserSession(servletRequest, servletResponse, contact);
        session.setAttribute("token", accessToken);


        return AppUtils.getRedirectUriResponse("/dashboard");


    }

}
