package com.authserver.api.constants;

import com.commons.Enum.AppMode;
import com.commons.utils.GaeUtil;

public final class ApiConstants {

    public static final String JSON_CONTENTTYPE = "application/json; charset=utf-8";
    public static final String AUTH_API_KEY = "ffbcca0f-5eb5-4585-a600-2f83dc243ab0";
    public static final AppMode APP_MODE ;

    static {

        APP_MODE = GaeUtil.getAppMode();

    }

    public static String gcsBucketName() {
        return "authserver-236711.appspot.com";
    }
}

