package org.oolong.entity.serializable;

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
        StringBuilder builder=new StringBuilder();
        builder.append("=================start=======================\n")
                .append("id: "+id+"\n");
        for(NodeDO node : nodeList){
            builder.append(node.toString()+"\n");
        }
        builder.append("===================end=======================\n");
        return builder.toString();
    }
}
