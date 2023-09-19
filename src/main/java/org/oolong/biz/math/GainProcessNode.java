package org.oolong.biz.math;

import org.oolong.entity.basic.ProcessNode;
import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;

/**
 * @Author: J.N
 * @Date 2023/9/19 23:46
 * @Version 1.0
 */
public class GainProcessNode extends ProcessNode {
    /**
     * 比例系数
     */
    private final int param;
    /**
     * 内部缓存值
     */
    private int value;

    public GainProcessNode(int param){
        this.param=param;
    }
    @Override
    public void input(int value) {
        this.value=value;
    }

    @Override
    public int output() {
        return this.value;
    }

    @Override
    public void process(RunConfig config, RunContext ctx) {
        this.value=this.value*this.param;
    }
}
