package com.authserver.app.services;

import com.commons.constants.CommonConstants;
import com.commons.entity.Contact;
import com.commons.entity.Token;
import com.commons.http.HttpMethod;
import com.commons.http.HttpRequest;
import com.commons.http.HttpResponse;
import com.commons.http.UrlFetcher;
import com.commons.utils.ObjUtil;
import com.commons.utils.Preconditions;

import java.util.Map;

public class UtilityServices {

    private static class UtilityServiceInit{
        private static final UtilityServices utilityServices = new UtilityServices();
    }

    public static UtilityServices getInstance(){
        return UtilityServiceInit.utilityServices;
    }

    public Token getByTokenFromRemote(String accessToken) {

        Preconditions.checkArgument(ObjUtil.isBlank(accessToken), "access token cannot be null/empty");

        try {
            HttpRequest request = new HttpRequest(CommonConstants.OAUTH_CATER_AUTH_URL + "/o/token/"+accessToken , HttpMethod.GET);

            request.addHeader("Authorization", "ApiKey="+CommonConstants.AUTH_API_KEY);
            HttpResponse response = UrlFetcher.makeRequest(request);
            if (!response.wasSuccessful()) {
                System.out.println("error response : " + response.getResponseContent());
                return null;
            }

            Map<String, Object> apiResponse = ObjUtil.getMapFromJson(response.getResponseContent());

            return ObjUtil.safeConvertMap((Map<String, Object>) ((Map<String, Object>) apiResponse.get("data")).get("token"), Token.class);
        } catch (Exception e) {
            System.out.println("exception while fetching contact by Id from remote : "+ e.getMessage()+ e);
            return null;
        }

    }

    public Contact getContactByUname(String uname) {

        Preconditions.checkArgument(ObjUtil.isBlank(uname), "user unique pin cannot be null/empty");

        try {
            HttpRequest request = new HttpRequest(CommonConstants.OAUTH_CATER_API_URL + "/api/v1/contact/uname/" + uname, HttpMethod.GET);
            //request.addHeader("Authorization", "ApiKey=" + constants.AUTH_API_KEY);

            HttpResponse response = UrlFetcher.makeRequest(request);
            if (!response.wasSuccessful()) {
                System.out.println("error response : " + response.getResponseContent());
                return null;
            }

            Map<String, Object> apiResponse = ObjUtil.getMapFromJson(response.getResponseContent());

            return ObjUtil.safeConvertMap((Map<String, Object>) ((Map<String, Object>) apiResponse.get("data")).get("contact"), Contact.class);
        } catch (Exception e) {
            System.out.println("exception while fetching contact by Id from remote : "+ e.getMessage());
            return null;
        }
    }
}
