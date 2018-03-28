package com.avantir.yoda.model;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

/**
 * Created by lekanomotayo on 05/03/2018.
 */
@Entity
@DynamicInsert
@Table(name = "oauth_client_details")
@Cacheable(true)
@SuppressWarnings("serial")
public class OAuthClientDetail extends BaseModel{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_id", nullable = false, unique = true)
    String clientId;
    @Column(name = "client_secret", nullable = false)
    String clientSecret;
    @Column(name = "resource_ids", nullable = true)
    String resourceIds;
    @Column(name = "scope", nullable = true)
    String scope; // read, write
    @Column(name = "authorized_grant_types", nullable = false)
    String authorizedGrantTypes;
    @Column(name = "web_server_redirect_uri", nullable = true)
    String webServerRedirectUri;
    @Column(name = "authorities", nullable = true)
    String authorities;
    @Column(name = "access_token_validity", nullable = false)
    int accessTokenValidity;
    @Column(name = "refresh_token_validity", nullable = true)
    int refreshTokenValidity;
    @Column(name = "additional_information", nullable = true)
    String additionalInformation;
    @Column(name = "autoapprove", nullable = true)
    String autoApprove;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUri() {
        return webServerRedirectUri;
    }

    public void setWebServerRedirectUri(String webServerRedirectUri) {
        this.webServerRedirectUri = webServerRedirectUri;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public int getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(int accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public int getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(int refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
