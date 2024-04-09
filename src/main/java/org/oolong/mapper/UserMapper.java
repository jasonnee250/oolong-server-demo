package org.oolong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.oolong.entity.db.User;
import org.springframework.stereotype.Repository;

/**
 * @author: wxm
 * @created: 2024/04/01
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    
    /**
     * select by name
     *
     * @param username name
     * @return result
     */
    User selectByName(@Param("username") String username);
    
    /**
     * select count by name
     *
     * @param username name
     * @return result
     */
    Integer selectCount(@Param("username") String username);
}
