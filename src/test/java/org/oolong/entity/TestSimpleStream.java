package org.oolong.entity;

import org.junit.Test;
import org.oolong.biz.math.GainProcessNode;
import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;
import org.oolong.entity.sink.AbsSinkNode;
import org.oolong.entity.source.AbsSourceNode;
import org.oolong.entity.stream.Stream;
import org.oolong.entity.sink.NormalSinkNode;
import org.oolong.entity.source.ConstSourceNode;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:33
 * @Version 1.0
 */
public class TestSimpleStream {

    @Test
    public void testStream(){
        Stream stream=new Stream();
        AbsSourceNode source=new ConstSourceNode(5);
        AbsSinkNode sink=new NormalSinkNode();
        GainProcessNode node=new GainProcessNode(2);
        RunConfig config=new RunConfig(10,1);
        RunContext ctx=new RunContext();
        stream.addSource(source);
        stream.addSink(sink);
        stream.pushNode(node);
        stream.run(config,ctx);
        System.out.println(ctx.getRunResult().toString());
    }
}
