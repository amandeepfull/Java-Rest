package com.commons.utils;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

@Slf4j
public class AppUtils {

    public static String getPagesPath() {
        return "/pages/";
    }

    public static String getHtmlPath(String fileName) {
        return getPagesPath() + "/html/" + fileName + ".html";
    }

   public static String getJspPagesPath(String fileName){
        return getPagesPath() + fileName +".jsp";
   }

    public static Response getRedirectUriResponse(String url) {

        return getRedirectUriResponse(url, Response.Status.FOUND);
    }

    public static String getJsPath(String fileName) {
        return "/react/" + fileName + ".js";
    }

    public static Response getRedirectUriResponse(String url, Response.Status status) {
        try {
            return Response.status(status).location(new URI(url)).build();
        } catch (URISyntaxException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static String utf8Encode(String data) {
        try {
            return URLEncoder.encode(data, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }


}


