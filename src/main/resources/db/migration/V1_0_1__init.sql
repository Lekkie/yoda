INSERT INTO tbl_users (username, password, enabled, created_by, created_on) VALUES ('Alex123', '$2a$04$pp0e0M1hkqeYlnvxQXb0MOMmR0yad4YDvY9FFaRbGRofHqc5A2OjS', 1, 'System', CURRENT_TIMESTAMP());

INSERT INTO oauth_client_details (client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove, created_by, created_on) VALUES ('devglan-client', 'devglan-secret', '', 'read,write,trust,openid,profile,api1', 'authorization_code,implicit,password,client_credentials,refresh_token','http://localhost:4200/auth-callback', 'READ', 3600, 21600, null, true, 'System', CURRENT_TIMESTAMP());

-- Sample Auhtorities
-- READ_PRIVILEDGE, WRITE_PRIVILEDGE
--"ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")

-- Sample scopes:
-- "read", "write", "trust"
-- openid – View your identity
-- profile – View your basic account info
-- client – Register and configure clients
-- realm – Administer the security realm