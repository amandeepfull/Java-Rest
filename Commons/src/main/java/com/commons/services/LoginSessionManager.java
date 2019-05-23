package com.commons.services;

import com.commons.Enum.AppMode;
import com.commons.constants.CommonConstants;
import com.commons.entity.Contact;
import com.commons.utils.ObjUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;


public class LoginSessionManager {

    public static final String SESSION_USER_ID = "_userId";
    public static final String SESSION_USER_CONTACT = "_userContact";

    public static boolean hasLoginSession(HttpSession session) {
        return currentUserId(session) != null;
    }

    public static String currentUserId(HttpSession session) {
        return session == null ? null : (String) session.getAttribute(SESSION_USER_ID);
    }

    public static Contact currentUser(HttpSession session) {
        return session == null ? null : (Contact) session.getAttribute(SESSION_USER_CONTACT);
    }

    public static void clearUserSession(HttpSession session) {
        if (session != null)
            session.invalidate();
    }

    public static HttpSession createUserSession(HttpServletRequest servletReq, HttpServletResponse servletResp, Contact contact) {

        if (servletReq == null || contact == null || contact.getId() == null)
            return null;

        HttpSession session = servletReq.getSession(true);

        session.setAttribute(SESSION_USER_ID, contact.getId());
        session.setAttribute(SESSION_USER_CONTACT, ObjUtil.getJson(contact));
        session.setMaxInactiveInterval(2 * 60 * 60);

        Cookie sessionCookie = new NewCookie("JSESSIONID", session.getId(), "/", null, null, 2 * 60 * 60, false);
        servletResp.setHeader("Set-Cookie", sessionCookie.toString() + ";HttpOnly");


        return session;
    }

}


