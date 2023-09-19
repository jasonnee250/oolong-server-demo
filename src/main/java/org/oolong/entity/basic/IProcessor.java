package org.oolong.entity.basic;

import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;

public interface IProcessor {
    /**
     * 处理回调
     */
    void process(RunConfig config, RunContext ctx);

    /**
     * 开始时回调
     */
    void start(RunConfig config, RunContext ctx);

    /**
     * 结束时回调
     */
    void stop(RunConfig config, RunContext ctx);
}
