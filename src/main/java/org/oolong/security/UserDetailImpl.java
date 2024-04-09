package org.oolong.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oolong.entity.db.User;
import org.oolong.exception.BizException;
import org.oolong.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailImpl implements UserDetailsService {
    
    private final UserMapper userMapper;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user = userMapper.selectByName(username);
        if (Objects.isNull(user)) {
            throw new BizException("[" + username + "] is not exist.");
        }
        
        SecurityUser securityUser = new SecurityUser();
        BeanUtils.copyProperties(user, securityUser);
        return securityUser;
    }
}
