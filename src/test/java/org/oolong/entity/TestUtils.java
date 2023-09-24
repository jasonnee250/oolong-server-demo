package org.oolong.entity;

import org.oolong.biz.math.GainProcessNode;
import org.oolong.entity.sink.AbsSinkNode;
import org.oolong.entity.sink.NormalSinkNode;
import org.oolong.entity.source.AbsSourceNode;
import org.oolong.entity.source.ConstSourceNode;
import org.oolong.entity.stream.Stream;

/**
 * @Author: J.N
 * @Date 2023/9/20 21:42
 * @Version 1.0
 */
public class TestUtils {

    public static Stream createStream(){
        Stream stream=new Stream();
        AbsSourceNode source=new ConstSourceNode(5);
        AbsSinkNode sink=new NormalSinkNode();
        GainProcessNode node=new GainProcessNode(2);
        stream.addSource(source);
        stream.addSink(sink);
        stream.pushNode(node);
        return stream;
    }

}
