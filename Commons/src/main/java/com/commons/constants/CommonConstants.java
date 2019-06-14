package com.commons.constants;

import com.commons.Enum.AppMode;
import com.commons.utils.GaeUtil;

public final class CommonConstants {

    public static final String JSON_CONTENTTYPE = "application/json; charset=utf-8";

    public static final String AUTH_API_KEY = "ffbcca0f-5eb5-4585-a600-2f83dc243ab0";

    public static final String OAUTH_CATER_API_URL = "https://api-dot-authserver-236711.appspot.com";

    public static final String  OAUTH_CATER_CLIENT_ID ="bd8e9c07-d9ec-402e-95d1-5d175cf8b785";
    public static final String  OAUTH_CATER_CLIENT_SECRET ="92f710bc-efb3-48bb-bd51-55b4024363a4";
    public static final String  OAUTH_CATER_AUTH_URL = "https://auth-dot-authserver-236711.appspot.com";
    public static final String OAUTH_CATER_APP_ID = "oauthcater_o9pfvoui";
    public static final String SIGN_SECRET_KEY = "9a97b0bc-bbe5-4472-88ee-13f62a56ee5d.86b53da4-0390-4d10-b7c4-021b3f5514ba" ;

    public static final String OAUTH_CATER_AUTH_CALLBACK ;
    public static final AppMode APP_MODE ;
    static {

        APP_MODE = GaeUtil.getAppMode();


        switch (APP_MODE){
            case LIVE:


                OAUTH_CATER_AUTH_CALLBACK  ="https://authserver-236711.appspot.com/auth/callback";
                break;
            default:

                OAUTH_CATER_AUTH_CALLBACK  ="http://localhost:8890/auth/callback";;

                break;

        }
    }


    public static String gcsBucketName() {
        return "authserver-236711.appspot.com";
    }
}

