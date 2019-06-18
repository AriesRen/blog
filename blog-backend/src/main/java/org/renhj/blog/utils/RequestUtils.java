package org.renhj.blog.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestUtils {
    public static String getIPAddress(HttpServletRequest request) {
        return request.getRemoteAddr();
    }
}
