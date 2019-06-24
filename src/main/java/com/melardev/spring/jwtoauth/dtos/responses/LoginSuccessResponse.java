package com.melardev.spring.jwtoauth.dtos.responses;

import com.melardev.spring.jwtoauth.entities.AppUser;

import java.util.HashMap;
import java.util.Map;

public class LoginSuccessResponse extends SuccessResponse {
    private final Map<String, Object> user;
    private String tokenScheme = "Bearer";
    private String token;

    private LoginSuccessResponse(String jwt, Map<String, Object> user) {
        this.token = jwt;
        this.user = user;
    }


    public static LoginSuccessResponse build(String token, AppUser user) {
        HashMap<String, Object> map = new HashMap<>(2);
        map.put("id", user.getId());
        map.put("username", user.getUsername());

        return new LoginSuccessResponse(token, map);
    }


    public Map<String, Object> getUser() {
        return user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenScheme() {
        return tokenScheme;
    }

    public void setTokenScheme(String tokenScheme) {
        this.tokenScheme = tokenScheme;
    }
}
