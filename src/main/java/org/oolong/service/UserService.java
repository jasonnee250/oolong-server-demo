package org.oolong.service;

import org.oolong.entity.db.ImageCode;
import org.oolong.entity.db.vo.RegisterUserVO;

/**
 * @author: wxm
 * @created: 2024/04/03
 */
public interface UserService {
    
    /**
     * register user
     *
     * @param registerUserVO registerVo
     * @return result
     */
    Boolean registerUser(RegisterUserVO registerUserVO);
    
    /**
     * get image code
     *
     * @return result
     */
    ImageCode getImageCode();
    
    /**
     * logout
     *
     * @param accessToken current token
     * @return result
     */
    Boolean logout(String accessToken);
}
