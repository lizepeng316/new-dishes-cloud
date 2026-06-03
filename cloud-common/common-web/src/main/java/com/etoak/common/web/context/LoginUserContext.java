package com.etoak.common.web.context;

import com.etoak.common.constant.CommonConstant;
import com.etoak.common.jwt.JwtUtil;
import com.etoak.common.web.util.ServletUtil;
import org.springframework.http.HttpHeaders;

import java.util.Map;

public class LoginUserContext {

    public static int getUserId(){
        String userId = getJwtMap().get(CommonConstant.LOGIN_USER_ID).toString();
        return Integer.parseInt(userId);
    }

    private static Map<String, Object> getJwtMap() {
        String token = ServletUtil.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
        return JwtUtil.parse(token);
    }
}
