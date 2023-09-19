package org.oolong.entity.sink;

import org.oolong.core.TValue;
import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:30
 * @Version 1.0
 */
public class NormalSinkNode extends AbsSinkNode{

    private int value;
    @Override
    public void input(int value) {
        this.value=value;
    }

    @Override
    public void process(RunConfig config, RunContext ctx) {
        ctx.getRunResult().tValueList.add(new TValue(ctx.currentTime,this.value));
    }
}
