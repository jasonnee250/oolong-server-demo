package org.oolong.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.oolong.security.SecurityUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * @author: wxm
 * @created: 2024/04/07
 */
public abstract class BaseController {
    
    /**
     * get current user
     */
    protected SecurityUser getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) principal;
            return JSON.parseObject(JSON.toJSONString(authentication.getPrincipal()), SecurityUser.class);
        } else if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication authentication = (OAuth2Authentication) principal;
            return JSON.parseObject(JSON.toJSONString(authentication.getPrincipal()), SecurityUser.class);
        }
        return new SecurityUser();
    }
    
    /**
     * get current userId
     */
    protected Long getUserId() {
        return this.getUser().getId();
    }
    
    /**
     * get current token
     */
    protected String getAccessToken() {
        Object principal = SecurityContextHolder.getContext().getAuthentication();
        if (principal instanceof OAuth2Authentication) {
            OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
            JSONObject detailObj = JSONObject.parseObject(JSONObject.toJSONString(oAuth2Authentication.getDetails()));
            return detailObj.getString("tokenValue");
        }
        return "";
    }
}
