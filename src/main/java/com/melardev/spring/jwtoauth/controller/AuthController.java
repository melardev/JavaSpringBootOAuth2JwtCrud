package com.melardev.spring.jwtoauth.controller;


import com.melardev.spring.jwtoauth.dtos.requests.LoginDto;
import com.melardev.spring.jwtoauth.dtos.responses.AppResponse;
import com.melardev.spring.jwtoauth.dtos.responses.ErrorResponse;
import com.melardev.spring.jwtoauth.dtos.responses.LoginSuccessResponse;
import com.melardev.spring.jwtoauth.dtos.responses.SuccessResponse;
import com.melardev.spring.jwtoauth.entities.AppUser;
import com.melardev.spring.jwtoauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;

@RestController
public class AuthController {

    @Autowired
    UserService userService;


    @Autowired
    TokenEndpoint tokenEndpoint;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JdbcClientDetailsService jdbcClientDetailsService;

    @GetMapping("/clients")
    public List<ClientDetails> listClientDetails() {
        return jdbcClientDetailsService.listClientDetails();
    }

    @PostMapping("/users")
    public ResponseEntity<AppResponse> registerUser(@Valid @RequestBody AppUser user, BindingResult result) {

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return new ResponseEntity<>(new ErrorResponse("Username already taken"), HttpStatus.BAD_REQUEST);
        }
        userService.createUser(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(new SuccessResponse("User registered successfully"), HttpStatus.OK);
    }


    @PostMapping("/auth/login")
    public ResponseEntity<AppResponse> login(@Valid @RequestBody LoginDto loginRequest, HttpServletRequest request, Principal principal) {

        String clientCredentials = request.getHeader("Authorization");
        if (clientCredentials != null && clientCredentials.toLowerCase().startsWith("basic ")) {

            byte[] base64Token = clientCredentials.substring(6).getBytes(StandardCharsets.UTF_8);
            byte[] decoded = Base64.getDecoder().decode(base64Token);
            String token = new String(decoded, StandardCharsets.UTF_8);
            String[] basicCredentials = token.split(":");
            if (basicCredentials.length == 2) {
                String oauth2ClientUsername = basicCredentials[0];

                ClientDetails user = jdbcClientDetailsService.loadClientByClientId(oauth2ClientUsername);
                if (passwordEncoder.matches(basicCredentials[1], user.getClientSecret())) {
                    Map<String, String> parameters = new HashMap<>();
                    parameters.put("username", loginRequest.getUsername());
                    parameters.put("password", loginRequest.getPassword());
                    parameters.put("grant_type", "password");

                    try {
                        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

                        principal = new UsernamePasswordAuthenticationToken(new User(oauth2ClientUsername, "", user.getAuthorities()),
                                null, authorities);

                        ResponseEntity<OAuth2AccessToken> oauthToken = tokenEndpoint.postAccessToken(principal, parameters);
                        OAuth2AccessToken body = oauthToken.getBody();

                        //noinspection OptionalGetWithoutIsPresent,ConstantConditions
                        return ResponseEntity.ok(LoginSuccessResponse.build(body.getValue(), userService.findByUsername(loginRequest.getUsername()).get()));
                    } catch (HttpRequestMethodNotSupportedException e) {
                        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(e.getMessage()));
                    }
                }
            }
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Invalid Credentials"));
    }
}
