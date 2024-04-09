package org.oolong.entity;

import org.oolong.biz.math.GainProcessNode;
import org.oolong.biz.sink.AbsSinkNode;
import org.oolong.biz.sink.NormalSinkNode;
import org.oolong.biz.source.AbsSourceNode;
import org.oolong.biz.source.ConstSourceNode;
import org.oolong.entity.basic.Node;
import org.oolong.entity.stream.Stream;

import java.util.HashMap;
import java.util.Map;

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

    public static Map<String, Node> createMap(){
        AbsSourceNode source=new ConstSourceNode(5);
        AbsSinkNode sink=new NormalSinkNode();
        GainProcessNode node=new GainProcessNode(2);
        Map<String, Node> map=new HashMap<>();
        map.put(source.getId(),source);
        map.put(sink.getId(),sink);
        map.put(node.getId(),node);
        return map;
    }

}
