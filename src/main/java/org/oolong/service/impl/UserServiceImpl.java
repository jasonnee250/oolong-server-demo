package org.oolong.service.impl;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.oolong.constant.RedisConstant;
import org.oolong.entity.db.ImageCode;
import org.oolong.entity.db.User;
import org.oolong.entity.db.vo.RegisterUserVO;
import org.oolong.exception.BizException;
import org.oolong.mapper.UserMapper;
import org.oolong.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author: wxm
 * @created: 2024/04/03
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserMapper userMapper;
    
    private final PasswordEncoder passwordEncoder;
    
    private final StringRedisTemplate redisTemplate;
    
    private final ConsumerTokenServices tokenServices;
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean registerUser(RegisterUserVO vo) {
        
        // image code validate
        this.verifyImageCode(vo.getCodeId(), vo.getImageCode());
        
        // account single validate
        Integer count = userMapper.selectCount(vo.getUsername());
        if (count > 0) {
            throw new BizException("[" + vo.getUsername() + "] is exist.");
        }
        
        // decode password
        String decodePw = new String(Base64Utils.decodeFromString(vo.getPassword()));
        
        User user = new User();
        user.setUsername(vo.getUsername());
        user.setPassword(passwordEncoder.encode(decodePw));
        user.setIsLocked(Boolean.FALSE);
        user.setIsEnabled(Boolean.TRUE);
        userMapper.insert(user);
        return Boolean.TRUE;
    }
    
    @Override
    public ImageCode getImageCode() {
        
        String codeId = UUID.randomUUID().toString();
        String redisKey = RedisConstant.IMAGE_CODE + codeId;
        
        Captcha captcha = new ArithmeticCaptcha(160, 60, 4);
        String code = captcha.text().toLowerCase();
        
        redisTemplate.opsForValue().set(redisKey, code, 5, TimeUnit.MINUTES);
        return new ImageCode(codeId, captcha.toBase64(""));
    }
    
    @Override
    public Boolean logout(String accessToken) {
        return tokenServices.revokeToken(accessToken);
    }
    
    /**
     * verify image code
     *
     * @param codeId code id
     * @param code   code value
     */
    private void verifyImageCode(String codeId, String code) {
        
        String redisKey = RedisConstant.IMAGE_CODE + codeId;
        String imageCode = redisTemplate.opsForValue().get(redisKey);
        redisTemplate.delete(redisKey);
        
        Assert.notNull(imageCode, "token is expired.");
        Assert.isTrue(code.equalsIgnoreCase(imageCode), "image code is illegal.");
    }
}
