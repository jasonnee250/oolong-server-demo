package org.oolong.entity.db.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
@Data
public class RefreshTokenGrantVO {
    
    /**
     * refresh token
     */
    @NotBlank(message = "refreshToken不能为空")
    private String refreshToken;
    
}
