package org.oolong.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
@Component
public class PlatformTokenEnhancer implements TokenEnhancer {
    
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (accessToken instanceof DefaultOAuth2AccessToken) {
            DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
            Map<String, Object> tokenInfo = new LinkedHashMap<>();
            SecurityUser principal = (SecurityUser) authentication.getPrincipal();
            tokenInfo.put("id", principal.getId());
            if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                tokenInfo.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }
            token.setAdditionalInformation(tokenInfo);
            return token;
        }
        return accessToken;
    }
}
