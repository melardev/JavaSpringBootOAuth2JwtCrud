package com.melardev.spring.jwtoauth.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.melardev.spring.jwtoauth.dtos.responses.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuthAccessDeniedHandler implements AccessDeniedHandler {

    @Autowired
    ObjectMapper mapper;

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ServletServerHttpResponse res = new ServletServerHttpResponse(httpServletResponse);
        res.setStatusCode(HttpStatus.UNAUTHORIZED);
        res.getServletResponse().setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        res.getBody().write(mapper.writeValueAsString(new ErrorResponse("You must provide valid credentials")).getBytes());
    }
}
