package com.avantir.yoda.model;

/**
 * Created by lekanomotayo on 05/03/2018.
 */

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@DynamicInsert
@Table(name = "tbl_oauth_client_additional_infos")
@Cacheable(true)
@SuppressWarnings("serial")
public class OAuthClientAdditionalInfo extends BaseModel{


    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client_id_id", nullable = false)
    long clientIdId;
    @Column(name = "key", nullable = false)
    String key;
    @Column(name = "value", nullable = false)
    String value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getClientIdId() {
        return clientIdId;
    }

    public void setClientIdId(long clientIdId) {
        this.clientIdId = clientIdId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
