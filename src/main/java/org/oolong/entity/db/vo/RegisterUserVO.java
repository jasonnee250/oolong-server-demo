package org.oolong.entity.db.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: wxm
 * @created: 2024/04/03
 */
@Data
public class RegisterUserVO {
    
    /**
     * 用户名称
     */
    @NotBlank(message = "username不能为空")
    private String username;
    
    /**
     * 用户密码  base64 加密
     */
    @NotBlank(message = "password不能为空")
    private String password;
    
    /**
     * 验证码id
     */
    @NotBlank(message = "codeId不能为空")
    private String codeId;
    
    /**
     * 验证码
     */
    @NotBlank(message = "imageCode不能为空")
    private String imageCode;
}
