package com.authserver.app.utils;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;


public final class HashUtil {

    public static String md5Hash(String src) {

        if (ObjUtil.isBlank(src))
            return null;
        return Hashing.md5().hashString(src, Charsets.UTF_8).toString();
    }
    public static String sha256(String base) {

        if (ObjUtil.isBlank(base))
            return null;
        return Hashing.sha256().hashString(base, Charsets.UTF_8).toString();
    }
}
