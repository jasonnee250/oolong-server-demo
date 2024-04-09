package org.oolong.security;

import org.oolong.entity.db.vo.LoginGrantVO;
import org.oolong.entity.db.vo.RefreshTokenGrantVO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.util.OAuth2Utils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2RequestValidator;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.endpoint.AbstractEndpoint;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
public class SecurityUnionEndpoint extends AbstractEndpoint {
    
    private PasswordEncoder passwordEncoder;
    
    private OAuth2RequestValidator oAuth2RequestValidator;
    
    private OAuthProperties oauthProperties;
    
    /**
     * password grant
     *
     * @param grantVO
     * @return
     */
    public OAuth2AccessToken resourceOwnerPasswordTokenGrant(LoginGrantVO grantVO) {
        
        // password decode
        byte[] passwordBytes = Base64Utils.decodeFromString(grantVO.getPassword());
        String password = new String(passwordBytes);
        
        // build oauth2 params
        Map<String, String> oauthReqParams = new HashMap<>();
        oauthReqParams.put(OAuth2Utils.CLIENT_ID, oauthProperties.getClientId());
        oauthReqParams.put(OAuth2Utils.GRANT_TYPE, oauthProperties.getGrantType());
        oauthReqParams.put(OAuth2Utils.SCOPE, oauthProperties.getScope());
        oauthReqParams.put("username", grantVO.getUsername());
        oauthReqParams.put("password", password);
        
        // load client info
        ClientDetails clientDetails = this.getClientDetailsService()
                .loadClientByClientId(oauthProperties.getClientId());
        Assert.notNull(clientDetails, "clientId is illegal");
        Assert.isTrue(passwordEncoder.matches(oauthProperties.getClientSecret(), clientDetails.getClientSecret()),
                "clientSecret is illegal");
        
        // creat oauth2 request
        TokenRequest tokenRequest = this.getOAuth2RequestFactory().createTokenRequest(oauthReqParams, clientDetails);
        
        // validate scope
        oAuth2RequestValidator.validateScope(tokenRequest, clientDetails);
        
        // obtain oauth2 token
        OAuth2AccessToken oAuth2AccessToken = getTokenGranter().grant(oauthProperties.getGrantType(), tokenRequest);
        return this.removeTokenInfo(oAuth2AccessToken);
    }
    
    /**
     * 刷新token
     *
     * @param grantVO
     * @return
     */
    public OAuth2AccessToken refreshTokenGrant(RefreshTokenGrantVO grantVO) {
        
        String clientId = oauthProperties.getClientId();
        String clientSecret = oauthProperties.getClientSecret();
        
        // build oauth2 params
        Map<String, String> oauthReqParams = new HashMap<>();
        oauthReqParams.put(OAuth2Utils.CLIENT_ID, clientId);
        oauthReqParams.put(OAuth2Utils.GRANT_TYPE, "refresh_token");
        oauthReqParams.put(OAuth2Utils.SCOPE, oauthProperties.getScope());
        oauthReqParams.put("refresh_token", grantVO.getRefreshToken());
        
        // load client info
        ClientDetails clientDetails = this.getClientDetailsService().loadClientByClientId(clientId);
        Assert.notNull(clientDetails, "clientId is illegal");
        Assert.isTrue(passwordEncoder.matches(clientSecret, clientDetails.getClientSecret()),
                "clientSecret is illegal");
        
        // creat oauth2 request
        TokenRequest tokenRequest = this.getOAuth2RequestFactory().createTokenRequest(oauthReqParams, clientDetails);
        
        // validate scope
        oAuth2RequestValidator.validateScope(tokenRequest, clientDetails);
        
        // obtain oauth2 token
        OAuth2AccessToken oAuth2AccessToken = getTokenGranter().grant("refresh_token", tokenRequest);
        return this.removeTokenInfo(oAuth2AccessToken);
    }
    
    
    /**
     * remove extra token info
     *
     * @param oAuth2AccessToken
     * @return
     */
    private OAuth2AccessToken removeTokenInfo(OAuth2AccessToken oAuth2AccessToken) {
        oAuth2AccessToken.getAdditionalInformation().clear();
        return oAuth2AccessToken;
    }
    
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    public void setoAuth2RequestValidator(OAuth2RequestValidator oAuth2RequestValidator) {
        this.oAuth2RequestValidator = oAuth2RequestValidator;
    }
    
    public void setOauthProperties(OAuthProperties oauthProperties) {
        this.oauthProperties = oauthProperties;
    }
    
}
