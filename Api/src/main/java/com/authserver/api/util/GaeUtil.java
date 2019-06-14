package com.authserver.api.util;


import com.authserver.api.enums.AppMode;
import com.google.appengine.api.utils.SystemProperty;

public class GaeUtil {

    public static boolean isDevEnvironment() {
        return (SystemProperty.environment.value() == SystemProperty.Environment.Value.Development);
    }

    public static AppMode getAppMode() {
        if (GaeUtil.isDevEnvironment())
            return AppMode.DEV;
        else
            return AppMode.LIVE;
    }

    public static String getAppId() {
        return System.getProperty(SystemProperty.applicationId.key());
    }
}
