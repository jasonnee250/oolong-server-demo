package org.oolong.controller;

import lombok.RequiredArgsConstructor;
import org.oolong.entity.basic.ResponseEntity;
import org.oolong.entity.db.vo.LoginGrantVO;
import org.oolong.entity.db.vo.RefreshTokenGrantVO;
import org.oolong.entity.db.vo.RegisterUserVO;
import org.oolong.security.SecurityUnionEndpoint;
import org.oolong.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final SecurityUnionEndpoint endpoint;
    
    private final UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity register(@Validated @RequestBody RegisterUserVO registerUserVO) {
        return ResponseEntity.success(userService.registerUser(registerUserVO));
    }
    
    @PostMapping("/login")
    public ResponseEntity login(@Validated @RequestBody LoginGrantVO grantVo) {
        return ResponseEntity.success(endpoint.resourceOwnerPasswordTokenGrant(grantVo));
    }
    
    @PostMapping("/refreshToken")
    public ResponseEntity refreshToken(@Validated @RequestBody RefreshTokenGrantVO grantVo) {
        return ResponseEntity.success(endpoint.refreshTokenGrant(grantVo));
    }
    
    @GetMapping("/imageCode")
    public ResponseEntity getImageCode() {
        return ResponseEntity.success(userService.getImageCode());
    }
    
}
