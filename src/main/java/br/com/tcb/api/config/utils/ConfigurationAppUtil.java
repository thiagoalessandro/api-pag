package br.com.intelector.api.config.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Getter
@Setter
@Component
public class ConfigurationAppUtil implements Serializable {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.authorized-grant-types}")
    private String[] authorizedGrantTypes;

    @Value("${security.oauth2.client.scope}")
    private String[] scope;

    @Value("${security.oauth2.client.access-token-validity-seconds}")
    private Integer acccessTokenValidSeconds;

    @Value("${security.oauth2.client.refresh-token-validity-seconds}")
    private Integer refreshTokenValidSeconds;

    @Value("${app.context.api}")
    private String appContextApi;

    @Value("${app.security.enable}")
    private Boolean appSecurityEnable;

}
