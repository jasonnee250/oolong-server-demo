package org.oolong.entity.serializable;

import com.alibaba.fastjson2.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.oolong.entity.basic.Node;
import org.oolong.entity.config.RunConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: J.N
 * @Date 2023/9/23 12:34
 * @Version 1.0
 */
@Getter
@Setter
public class PageDO implements Serializable {

    @JsonProperty(value = "id")
    String id;
    @JsonProperty(value = "nodeList")
    List<NodeDO> nodeList;
    @JsonProperty(value = "streamList")
    List<StreamDO> streamList;
    @JsonProperty(value = "config")
    RunConfig config;

    public PageDO(){
        this.id="";
        streamList=new ArrayList<>();
        nodeList=new ArrayList<>();
        this.config=new RunConfig();
    }

    public PageDO(String id,RunConfig config){
        this.id=id;
        streamList=new ArrayList<>();
        nodeList=new ArrayList<>();
        this.config=config;
    }

    public void addStreamDO(StreamDO streamDO){
        streamList.add(streamDO);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
