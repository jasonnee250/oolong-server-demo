package org.oolong.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "oauth")
public class OAuthProperties {
    
    /**
     * sign
     */
    private String signingKey;
    
    /**
     * client id
     */
    private String clientId;
    
    /**
     * secret
     */
    private String clientSecret;
    
    /**
     * grant type, default is password
     */
    private String grantType;
    
    /**
     * grant scope
     */
    private String scope;
    
}
