package org.oolong.entity.context;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: J.N
 * @Date 2023/9/20 00:04
 * @Version 1.0
 */
@Getter
@Setter
public class RunContext {

    public float currentTime;
    RunResult runResult;

    public RunContext(){
        currentTime=0;
        runResult=new RunResult();
    }
}
