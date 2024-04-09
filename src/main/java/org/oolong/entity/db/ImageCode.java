package org.oolong.entity.db;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: wxm
 * @created: 2024/04/03
 */
@Data
@AllArgsConstructor
public class ImageCode {
    
    /**
     * 验证码id
     */
    private String codeId;
    
    /**
     * 验证码图片(base64编码字符串)
     */
    private String imageCode;
}
