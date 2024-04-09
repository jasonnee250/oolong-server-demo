package org.oolong.entity.context;

import org.oolong.core.TValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: J.N
 * @Date 2023/9/20 00:11
 * @Version 1.0
 */
public class RunResult {
    public List<TValue> tValueList;

    public RunResult(){
        tValueList=new ArrayList<>();
    }
    @Override
    public String toString(){
        StringBuilder builder=new StringBuilder();
        builder.append("===========print the result====================\n")
                .append("t  | v\n");
        for(TValue p:tValueList){
            builder.append(p.getT())
                    .append("  | ")
                    .append(p.getValue())
                    .append("\n");
        }
        builder.append("============end the result=====================\n");
        return builder.toString();
    }
}
