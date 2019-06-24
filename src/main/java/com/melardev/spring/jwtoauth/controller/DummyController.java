package com.melardev.spring.jwtoauth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/dummy")
public class DummyController {
    @Autowired
    ObjectMapper objectMapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUser(Principal principal) {
        try {
            return objectMapper.writeValueAsString(principal);
        } catch (JsonProcessingException e) {
            return "{\"success:\":false}";
        }
    }
}
