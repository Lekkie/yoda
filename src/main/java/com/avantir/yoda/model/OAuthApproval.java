package com.avantir.yoda.model;

/**
 * Created by lekanomotayo on 05/03/2018.
 */

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@Table(name = "oauth_approval")
@Cacheable(true)
@SuppressWarnings("serial")
public class OAuthApproval extends BaseModel{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", nullable = false)
    String userId;
    @Column(name = "client_id", nullable = false)
    String clientId;
    @Column(name = "scope", nullable = true)
    String scope;
    @Column(name = "status", nullable = true)
    String status;
    @Column(name = "expires_at", nullable = false)
    Date expiresAt;
    @Column(name = "last_modified_at", nullable = true)
    Date lastModifiedAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
