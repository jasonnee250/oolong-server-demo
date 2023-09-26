package org.oolong.biz.initial;

import org.oolong.biz.error.DataError;
import org.oolong.biz.math.GainProcessNode;
import org.oolong.entity.basic.Node;
import org.oolong.entity.basic.ProcessNode;
import org.oolong.entity.basic.SubType;
import org.oolong.entity.doc.Page;
import org.oolong.entity.serializable.NodeDO;
import org.oolong.entity.serializable.PageDO;
import org.oolong.entity.serializable.StreamDO;
import org.oolong.biz.sink.AbsSinkNode;
import org.oolong.biz.sink.NormalSinkNode;
import org.oolong.biz.source.AbsSourceNode;
import org.oolong.biz.source.ConstSourceNode;
import org.oolong.entity.stream.Stream;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: J.N
 * @Date 2023/9/24 15:35
 * @Version 1.0
 */
public class InitFactory {

    public static AbsSinkNode createSinkNode(NodeDO nodeDO){
        SubType subType=nodeDO.getNodeType().getSubType();
        switch (subType){
            case NORMAL_SINK:
                return new NormalSinkNode(nodeDO.getId());
            default:
                throw new DataError("Sink node type error,and the sub type is"+subType);
        }
    }

    public static AbsSourceNode createSourceNode(NodeDO nodeDO){
        SubType subType=nodeDO.getNodeType().getSubType();
        switch (subType){
            case CONST_SOURCE:
                Object v=nodeDO.getParams().get("value");
                return new ConstSourceNode(nodeDO.getId(),(int)v);
            default:
                throw new DataError("Source node type error,and the sub type is"+subType);
        }
    }

    public static ProcessNode createProcessNode(NodeDO nodeDO){
        SubType subType=nodeDO.getNodeType().getSubType();
        switch (subType){
            case GAIN_PROCESS:
                Object v=nodeDO.getParams().get("param");
                return new GainProcessNode(nodeDO.getId(),(int)v);
            default:
                throw new DataError("Source node type error,and the sub type is"+subType);
        }
    }

    public static Node crateNode(NodeDO nodeDO){
        switch (nodeDO.getNodeType().getBasicType()){
            case SOURCE:
                return createSourceNode(nodeDO);
            case SINK:
                return createSinkNode(nodeDO);
            case PROCESS:
                return createProcessNode(nodeDO);
        }
        return null;
    }

    public static Stream createStream(StreamDO streamDO){
        Stream stream=new Stream(streamDO.getId());
        List<NodeDO> nodeDOList=streamDO.getNodeList();
        if(nodeDOList.size()<2){
            return stream;
        }
        stream.addSource(createSourceNode(nodeDOList.get(0)));
        stream.addSink(createSinkNode(nodeDOList.get(nodeDOList.size()-1)));
        for(int i=1;i<nodeDOList.size()-1;i++){
            stream.pushNode(createProcessNode(nodeDOList.get(i)));
        }
        return stream;
    }

    public static Page createPage(PageDO pageDO){
        List<Stream> streams=new ArrayList<>();
        for(StreamDO streamDO: pageDO.getStreamList()){
            streams.add(createStream(streamDO));
        }
        return Page.createPage(pageDO.getId(),streams,pageDO.getConfig());
    }
}
