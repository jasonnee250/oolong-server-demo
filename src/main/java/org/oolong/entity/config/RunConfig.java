package org.oolong.entity.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: J.N
 * @Date 2023/9/19 23:56
 * @Version 1.0
 * 运行配置类
 */
public class RunConfig {
    @Setter @Getter
    public float simulateTime=1.0f;
    @Setter @Getter
    public float stepTime=0.1f;

    public RunConfig(float simulateTime,float stepTime){
        this.simulateTime=simulateTime;
        this.stepTime=stepTime;
    }
}
