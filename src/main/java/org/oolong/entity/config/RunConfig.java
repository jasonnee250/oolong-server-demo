package org.oolong.entity.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: J.N
 * @Date 2023/9/19 23:56
 * @Version 1.0
 * 运行配置类
 */
@Setter @Getter
public class RunConfig {

    public float simulateTime=1.0f;
    public float stepTime=0.1f;

    public RunConfig(float simulateTime,float stepTime){
        this.simulateTime=simulateTime;
        this.stepTime=stepTime;
    }

    @Override
    public String toString() {
        StringBuilder builder=new StringBuilder();
        builder.append("=============run config==============\n")
                .append("simulate time: "+simulateTime+"\n")
                .append("step time: "+stepTime+"\n")
                .append("===========================\n");
        return builder.toString();
    }
}
