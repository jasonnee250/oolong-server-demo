package org.oolong.entity.serializable;

import lombok.Getter;
import org.oolong.entity.basic.BizNodeType;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: J.N
 * @Date 2023/9/23 11:45
 * @Version 1.0
 */
public class NodeDO implements Serializable {
    @Getter
    String id;
    @Getter
    BizNodeType bizNodeType;
    @Getter
    Map<String,Object> params;

    public NodeDO(String id, BizNodeType nodeType){
        this.id=id;
        this.bizNodeType=nodeType;
        this.params=new HashMap<>();
    }

    public void setParam(String key,Object value){
        this.params.put(key,value);
    }

    @Override
    public String toString(){
        StringBuilder builder=new StringBuilder();
        builder.append("=================start=======================\n")
                .append("id: "+id+"\n")
                .append("node type: "+bizNodeType+"\n");
        for(Map.Entry<String,Object> entry : params.entrySet()){
            builder.append(entry.getKey()+" : "+entry.getValue()+"\n");
        }
        builder.append("===================end=======================\n");
        return builder.toString();
    }
}
