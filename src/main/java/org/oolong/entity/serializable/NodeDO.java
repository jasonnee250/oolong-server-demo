package org.oolong.entity.serializable;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;
import org.oolong.entity.basic.BizNodeType;
import org.oolong.entity.props.DescriptionProps;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: J.N
 * @Date 2023/9/23 11:45
 * @Version 1.0
 */
@Getter @Setter
public class NodeDO implements Serializable {

    String id;

    BizTypeDO bizNodeType;

    Map<String,Object> params;

    //样式信息
    DescriptionProps descriptionProps;

    public NodeDO(){}

    public NodeDO(String id, BizNodeType nodeType, DescriptionProps descriptionProps){
        this.id=id;
        this.bizNodeType=nodeType.toDO();
        this.descriptionProps=descriptionProps;
        this.params=new HashMap<>();
    }

    public void setParam(String key,Object value){
        this.params.put(key,value);
    }

    @Override
    public String toString(){
        return JSON.toJSONString(this);
    }
}
