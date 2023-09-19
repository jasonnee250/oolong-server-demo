package org.oolong.entity.basic;

import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:15
 * @Version 1.0
 */
public abstract class Node extends Unit implements IProcessor {

    static String PREFIX = "node-";
    static int num = 0;

    public Node() {
        super(PREFIX + num);
        num++;
    }

    @Override
    public void start(RunConfig config, RunContext ctx) {
    }

    @Override
    public void stop(RunConfig config, RunContext ctx) {
    }
}
