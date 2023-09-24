package org.oolong.entity.serializable;

import lombok.Getter;
import lombok.Setter;
import org.oolong.entity.config.RunConfig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: J.N
 * @Date 2023/9/23 12:34
 * @Version 1.0
 */
@Getter
@Setter
public class PageDO implements Serializable {

    List<StreamDO> streamList;

    RunConfig config;

    public PageDO(RunConfig config){
        streamList=new ArrayList<>();
        this.config=config;
    }

    public void addStreamDO(StreamDO streamDO){
        streamList.add(streamDO);
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("=============run config==============\n");
        builder.append(config.toString());
        for(StreamDO streamDO:streamList){
            builder.append(streamDO.toString());
        }
        builder.append("===========================\n");
        return builder.toString();
    }
}
