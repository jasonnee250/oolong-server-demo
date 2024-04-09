package org.oolong.entity.db;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.apache.ibatis.type.EnumTypeHandler;

import java.time.LocalDateTime;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
@Data
public class User extends BaseEntity {
    
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    
    /**
     * 用户名
     */
    private String username;
    
    /**
     * 密码
     */
    private String password;
    
    /**
     * 账户是否被锁定
     */
    private Boolean isLocked;
    
    /**
     * 账户是否过期
     */
    private LocalDateTime expired;
    
    /**
     * 密码是否过期
     */
    private LocalDateTime passwordExpired;
    
    /**
     * 账户是否可用
     */
    private Boolean isEnabled;
    
}
