package com.avantir.yoda.model;

/**
 * Created by lekanomotayo on 05/03/2018.
 */

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Table(name = "tbl_client_details")
@Cacheable(true)
@SuppressWarnings("serial")
public class ClientDetails extends BaseModel{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "app_id", nullable = false)
    String appId;
    @Column(name = "resource_ids", nullable = false)
    String resourceIds;
    @Column(name = "app_secret", nullable = false)
    String appSecret;
    @Column(name = "scope", nullable = false)
    String scope;
    @Column(name = "grant_types", nullable = false)
    String grantTypes;
    @Column(name = "redirect_url", nullable = false)
    String redirectUrl;
    @Column(name = "authorities", nullable = false)
    String authorities;
    @Column(name = "access_token_validity", nullable = false)
    int accessTokenValidity;
    @Column(name = "refresh_token_validity", nullable = false)
    int refreshTokenValidity;
    @Column(name = "additional_information", nullable = false)
    String additionalInformation;
    @Column(name = "auto_approve_scopes", nullable = false)
    String autoApproveScopes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
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

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoApproveScopes() {
        return autoApproveScopes;
    }

    public void setAutoApproveScopes(String autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }
}
