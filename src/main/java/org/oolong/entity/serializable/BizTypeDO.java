package org.oolong.entity.serializable;

import lombok.Getter;
import lombok.Setter;
import org.oolong.entity.basic.BizNodeType;

/**
 * @Author: J.N
 * @Date 2023/10/1 13:52
 * @Version 1.0
 */
@Getter @Setter
public class BizTypeDO {
    String basicType;
    String subType;

    public BizTypeDO(){}

    public BizTypeDO(String basic,String sub){
        this.basicType=basic;
        this.subType=sub;
    }
}
