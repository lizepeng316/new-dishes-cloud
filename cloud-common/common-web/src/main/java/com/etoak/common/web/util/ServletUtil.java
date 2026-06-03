package com.etoak.common.web.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ServletUtil {

    public static HttpServletRequest getRequest() {
        return getAttributes().getRequest();
    }
    public static HttpServletResponse getResponse() {
        return getAttributes().getResponse();
    }

    private static ServletRequestAttributes getAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }
}
