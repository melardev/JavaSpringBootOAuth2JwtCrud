package com.melardev.spring.jwtoauth.config;

import com.melardev.spring.jwtoauth.security.OAuthAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    // This is a Different Server
    // ResourceServerConfig is not the same as OAuth2ServerConfig
    // This is the configuration you would need if it was a different server,
    // Remember, in our case you could even leave this class empty, the app would still work
    // because interesting beans are already exposed by OAuth2AuthorizationServerConfig


    @Value("${app.security.jwt.secret}")
    private String jwtSecret;

    @Autowired
    private AuthenticationEntryPoint oauthEntryPoint;

    @Autowired
    private OAuthAccessDeniedHandler oauthAccessDeniedHandler;

    private JwtAccessTokenConverter accessTokenConverter;

    @Override
    public void configure(ResourceServerSecurityConfigurer config) {
        config.tokenServices(tokenServices());

        config.authenticationEntryPoint(oauthEntryPoint);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().disable()
                .authorizeRequests()
                .antMatchers("/dummy/**").authenticated()
                .and().exceptionHandling()
                .authenticationEntryPoint(oauthEntryPoint)
                .accessDeniedHandler(oauthAccessDeniedHandler);

    }

    // Notice I have to provide different bean names because otherwise
    // they clash with the bean names already exposed on AuthorizationServer
    // @Bean("resourceServerJwtStore")
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean("resourceServerTokenConverter")
    public JwtAccessTokenConverter accessTokenConverter() {
        if (accessTokenConverter == null) {
            accessTokenConverter = new JwtAccessTokenConverter();
            accessTokenConverter.setSigningKey(jwtSecret);
            // accessTokenConverter.setVerifier(new MacSigner(jwtSecret));
        }
        return accessTokenConverter;
    }

    // @Bean("resourceServerTokenServices")
    // @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        return defaultTokenServices;
    }

}
// WARNING: the JwtAccessTokenConverter is has to be a been ot be initialized appropriately
// because it implements InitializingBean, without being a Bean afterPropertiesSet() won't be called
// so some fields(i.e. verifier won't be initialized)