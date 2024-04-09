package org.oolong.entity.serializable;

import com.alibaba.fastjson2.JSON;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: J.N
 * @Date 2023/9/20 22:17
 * @Version 1.0
 */
public class StreamDO implements Serializable {

    @Getter
    String id;
    @Getter
    List<NodeDO> nodeList;

    public StreamDO(String id){
        this.id=id;
        this.nodeList=new ArrayList<>();
    }

    public void addNode(NodeDO nodeDO){
        nodeList.add(nodeDO);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
