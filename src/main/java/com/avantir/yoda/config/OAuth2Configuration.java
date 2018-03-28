package com.avantir.yoda.config;

import com.avantir.yoda.model.User;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.*;

/**
 * Created by lekanomotayo on 05/03/2018.
 */

@Configuration
@EnableAuthorizationServer
public class OAuth2Configuration extends AuthorizationServerConfigurerAdapter {

    static final String CLIEN_ID = "devglan-client";
    static final String CLIENT_SECRET = "devglan-secret";
    static final String GRANT_TYPE_PASSWORD = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
    static final String CLIENT_CREDENTIALS = "client_credentials";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private UserApprovalHandler userApprovalHandler;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Resource(name = "userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    CustomTokenEnhancer customTokenEnhancer = new CustomTokenEnhancer();

    @Autowired
    HikariDataSource dataSource;

    /*
    @Value("${yoda.keystore.file}")
    String keystore;
    @Value("${yoda.keystore.password}")
    String keystorePassword;
    @Value("${yoda.keystore.jwtAlias}")
    String jwtAlias;
    */





    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        //JdbcClientDetailsService
        configurer.jdbc(dataSource);
        /*
        configurer.inMemory()
                .withClient(CLIEN_ID)
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT )
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS).
                refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);
        */
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        //CustomTokenEnhancer customTokenEnhancer = new CustomTokenEnhancer();
        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(customTokenEnhancer)
                .tokenEnhancer(customTokenEnhancer)
                .userApprovalHandler(userApprovalHandler)
                .userDetailsService(userDetailsService)
                .authenticationManager(authenticationManager);
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception
    {
        oauthServer.checkTokenAccess("isAuthenticated()");
    }


    @Bean
    @Autowired
    public TokenStore tokenStore(JwtAccessTokenConverter tokenStore) {
        return new JwtTokenStore(tokenStore);
        //return new JdbcTokenStore(dataSource);
    }


    @Bean
    protected JwtAccessTokenConverter jwtTokenEnhancer(final @Value("${yoda.keystore.file}") String keystore,
                                                       @Value("${yoda.keystore.password}") String keystorePassword,
                                                       final @Value("${yoda.keystore.jwtAlias}") String jwtAlias) {
        org.springframework.core.io.Resource filePathResource = new FileSystemResource(keystore);
        //org.springframework.core.io.Resource classPathResource = new ClassPathResource(keystore);
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(filePathResource, keystorePassword.toCharArray());
        customTokenEnhancer.setKeyPair(keyStoreKeyFactory.getKeyPair(jwtAlias));
        return customTokenEnhancer;
    }


    protected static class CustomTokenEnhancer extends JwtAccessTokenConverter {
        @Override
        public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

            Map<String, Object> info = new LinkedHashMap<String, Object>(accessToken.getAdditionalInformation());
            Object principal = authentication.getPrincipal();

            if(principal instanceof org.springframework.security.core.userdetails.User){
                org.springframework.security.core.userdetails.User user = (org.springframework.security.core.userdetails.User)principal;
                info.put("email", user.getUsername());
            }

            DefaultOAuth2AccessToken customAccessToken = new DefaultOAuth2AccessToken(accessToken);

            // Get the authorities from the user
            Set<GrantedAuthority> authoritiesSet = new HashSet<>(authentication.getAuthorities());

            // Generate String array
            String[] authorities = new String[authoritiesSet.size()];

            int i = 0;
            for (GrantedAuthority authority : authoritiesSet)
                authorities[i++] = authority.getAuthority();

            info.put("authorities", authorities);
            customAccessToken.setAdditionalInformation(info);

            return super.enhance(customAccessToken, authentication);
        }
    }


}
