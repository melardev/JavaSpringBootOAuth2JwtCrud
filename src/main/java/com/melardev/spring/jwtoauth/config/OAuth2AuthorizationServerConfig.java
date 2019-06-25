package com.melardev.spring.jwtoauth.config;

import com.melardev.spring.jwtoauth.security.CustomTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;


    @Value("${app.security.oauth2.client1.id}")
    private String client1Id;
    @Value("${app.security.oauth2.client1.password}")
    private String client1Password;
    @Value("${app.security.oauth2.client1.scopes}")
    private List<String> client1Scopes;

    @Value("${app.security.oauth2.client2.id}")
    private String client2Id;
    @Value("${app.security.oauth2.client2.password}")
    private String client2Password;
    @Value("${app.security.oauth2.client2.scopes}")
    private List<String> client2Scopes;

    @Value("${app.security.oauth2.grant_types.password}")
    private String passwordGrantType;
    @Value("${app.security.oauth2.grant_types.authorization_code}")
    private String authorizationCodeGrantType;
    @Value("${app.security.oauth2.grant_types.refresh_token}")
    private String refreshTokenGrantType;
    @Value("${app.security.oauth2.grant_types.implicit}")
    private String implicitGrantType;


    @Value("${app.security.oauth2.access_token_validity_seconds}")
    private int accessTokenValiditySeconds = 1 * 60 * 60 * 24 * 30;
    @Value("${app.security.oauth2.refresh_token_validity_seconds}")
    private int refreshTokenValiditySeconds = 1 * 60 * 60 * 24 * 30;

    @Value("${app.security.jwt.secret}")
    private String jwtSecret;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    DataSource dataSource;

    private JwtAccessTokenConverter accessTokenConverter;


    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        if (accessTokenConverter == null) {
            accessTokenConverter = new JwtAccessTokenConverter();
            accessTokenConverter.setSigningKey(jwtSecret);
        }
        return accessTokenConverter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        // In Memory
        // configureInMemoryClientDetailsService(configurer);

        // JDBC
        // Approach 1
        // configurer.jdbc(dataSource).passwordEncoder(passwordEncoder);
        // Approach 2
        configurer.withClientDetails(jdbcDetailsService(dataSource));

        // Custom
        // configurer.withClientDetails(new AppClientsService());
    }

    private void configureInMemoryClientDetailsService(ClientDetailsServiceConfigurer configurer) throws Exception {

        InMemoryClientDetailsServiceBuilder inMemoryClientDetailsBuilder = configurer.inMemory();

        // Client 1
        ClientDetailsServiceBuilder.ClientBuilder client1DetailsBuilder = inMemoryClientDetailsBuilder.withClient(client1Id)
                .secret(passwordEncoder.encode(client1Password))
                .authorizedGrantTypes(passwordGrantType, authorizationCodeGrantType, refreshTokenGrantType, implicitGrantType)
                .authorities("ROLE_ADMIN")
                .accessTokenValiditySeconds(accessTokenValiditySeconds).
                        refreshTokenValiditySeconds(refreshTokenValiditySeconds);

        String[] stringArr = new String[client1Scopes.size()];
        client1DetailsBuilder.scopes(client1Scopes.toArray(stringArr));


        // Client 2
        ClientDetailsServiceBuilder.ClientBuilder client2DetailsBuilder = client1DetailsBuilder
                .and()
                .withClient(client2Id)
                .secret(passwordEncoder.encode(client2Password))
                .authorizedGrantTypes(passwordGrantType, authorizationCodeGrantType, refreshTokenGrantType, implicitGrantType)
                .authorities("ROLE_USER")
                .accessTokenValiditySeconds(accessTokenValiditySeconds).
                        refreshTokenValiditySeconds(refreshTokenValiditySeconds);


        for (String scope : client2Scopes) client2DetailsBuilder.scopes(scope);

    }

    @SuppressWarnings("Duplicates")
    @Bean
    JdbcClientDetailsService jdbcDetailsService(DataSource dataSource) {
        String[] stringArr = new String[client1Scopes.size()];

        JdbcClientDetailsService jdbcClientDetailsService = new JdbcClientDetailsService(dataSource);
        jdbcClientDetailsService.setPasswordEncoder(passwordEncoder);

        // User 1
        try {
            jdbcClientDetailsService.removeClientDetails(client1Id);
        } catch (NoSuchClientException ignored) {
        }

        BaseClientDetails user1 = new BaseClientDetails(client1Id, null, StringUtils.collectionToCommaDelimitedString(client1Scopes),
                StringUtils.collectionToCommaDelimitedString(Arrays.asList(passwordGrantType, refreshTokenGrantType)), "ROLE_ADMIN");

        // WE SHOULD NOT ENCRYPT THE PASSWORD HERE, THIS IS DONE FOR US IN addClientDetails()
        user1.setClientSecret(client1Password);
        user1.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        user1.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        jdbcClientDetailsService.addClientDetails(user1);

        // User 2
        try {
            jdbcClientDetailsService.removeClientDetails(client2Id);
        } catch (NoSuchClientException ignored) {
        }
        BaseClientDetails user2 = new BaseClientDetails(client2Id, null, StringUtils.collectionToCommaDelimitedString(client2Scopes),
                StringUtils.collectionToCommaDelimitedString(Arrays.asList(passwordGrantType, refreshTokenGrantType)), "ROLE_USER");

        // WE SHOULD NOT ENCRYPT THE PASSWORD HERE, THIS IS DONE FOR US IN addClientDetails()
        user2.setClientSecret(client2Password);
        user2.setRefreshTokenValiditySeconds(refreshTokenValiditySeconds);
        user2.setAccessTokenValiditySeconds(accessTokenValiditySeconds);
        jdbcClientDetailsService.addClientDetails(user2);

        return jdbcClientDetailsService;
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(new CustomTokenEnhancer(), accessTokenConverter()));

        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .tokenEnhancer(tokenEnhancerChain);
    }


    @Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }
}