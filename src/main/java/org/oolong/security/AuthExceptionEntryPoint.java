package org.oolong.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.oolong.utils.TimeUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
public class AuthExceptionEntryPoint extends OAuth2AuthenticationEntryPoint {
    
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws ServletException {
        
        Throwable cause = authException.getCause();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        map.put("path", request.getServletPath());
        map.put("time", TimeUtil.timeFormatNow());
        map.put("msg", cause instanceof InvalidTokenException ? "token is illegal." : "token is missing.");
        
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
        } catch (IOException e) {
            throw new ServletException();
        }
    }
}
