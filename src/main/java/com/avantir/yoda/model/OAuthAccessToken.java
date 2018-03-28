package com.avantir.yoda.model;

/**
 * Created by lekanomotayo on 05/03/2018.
 */

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Table(name = "oauth_access_token")
@Cacheable(true)
@SuppressWarnings("serial")
public class OAuthAccessToken extends BaseModel{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "authentication_id", nullable = false)
    String authenticationId;
    @Column(name = "token_id", nullable = false)
    String tokenId;
    @Column(name = "token", nullable = false, length = 5000, unique = true)
    byte[] token;
    @Column(name = "user_name", nullable = true)
    String username;
    @Column(name = "client_id", nullable = false)
    String clientId;
    @Column(name = "authentication", nullable = false, length = 5000)
    byte[] authentication;
    @Column(name = "refresh_token", nullable = true)
    String refreshToken;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthenticationId() {
        return authenticationId;
    }

    public void setAuthenticationId(String authenticationId) {
        this.authenticationId = authenticationId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
