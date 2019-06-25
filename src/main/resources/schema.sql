-- The Schema reference is at:
-- https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
-- If using MySQL you would need to create the schema and USE it
CREATE TABLE IF NOT EXISTS oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256) NOT NULL,
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4000),
    autoapprove             VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_client_token
(
    token_id          VARCHAR(256),
    token             BLOB,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_access_token
(
    token_id          VARCHAR(256),
    token             BLOB,
    authentication_id VARCHAR(256),
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication    BLOB,
    refresh_token     VARCHAR(256)
);

CREATE TABLE IF NOT EXISTS oauth_refresh_token
(
    token_id       VARCHAR(256),
    token          BLOB,
    authentication BLOB
);

CREATE TABLE IF NOT EXISTS oauth_code
(
    code           VARCHAR(256),
    authentication BLOB
);

CREATE TABLE IF NOT EXISTS oauth_approvals
(
    userId         VARCHAR(256),
    clientId       VARCHAR(256),
    scope          VARCHAR(256),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

-- This is how we add clients using SQL, the best reference for SQL statements with Spring Security OAuth2 is the JdbcClientDetailsService.java
-- Source code itself
insert into oauth_client_details (client_secret, resource_ids, scope, authorized_grant_types,
                                  authorities, access_token_validity, refresh_token_validity,
                                  client_id)
values ('{bcrypt}$2a$10$nnT1Jj/87Htbfu7s7ElwBOjQwHEFe.g4jCETTFJNjkF9/MgGA1OXG', '', 'read,write,trust',
        'password,refresh_token', 'ROLE_USER',
        2592000, 2592000, 'sqlClient')