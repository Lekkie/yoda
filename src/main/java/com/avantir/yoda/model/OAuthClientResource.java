package com.avantir.yoda.model;

/**
 * Created by lekanomotayo on 05/03/2018.
 */

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Table(name = "tbl_oauth_client_resources")
@Cacheable(true)
@SuppressWarnings("serial")
public class OAuthClientResource extends BaseModel{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_id", nullable = false)
    String clientId;
    @Column(name = "resource_id", nullable = false)
    String resourceId;

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

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
