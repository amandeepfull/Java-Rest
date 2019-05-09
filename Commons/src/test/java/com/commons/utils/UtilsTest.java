package com.commons.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void testGetHost() {

        String str = removeServletPath("https://authserver-236711.appspot.com/auth", "/auth");
        System.out.println(str);

    }

    private static String removeServletPath(String s, String s1) {

        StringBuilder sb = new StringBuilder(s);
        sb.delete(s.lastIndexOf("/auth"), 42);
        System.out.println("sb :"+sb.toString());

        return null;
    }
}

