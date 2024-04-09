package org.oolong.biz.initial;

import org.oolong.biz.error.DataError;
import org.oolong.biz.math.GainProcessNode;
import org.oolong.entity.basic.*;
import org.oolong.entity.doc.Page;
import org.oolong.entity.serializable.BizTypeDO;
import org.oolong.entity.serializable.NodeDO;
import org.oolong.entity.serializable.PageDO;
import org.oolong.entity.serializable.StreamDO;
import org.oolong.biz.sink.AbsSinkNode;
import org.oolong.biz.sink.NormalSinkNode;
import org.oolong.biz.source.AbsSourceNode;
import org.oolong.biz.source.ConstSourceNode;
import org.oolong.entity.stream.Stream;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: J.N
 * @Date 2023/9/24 15:35
 * @Version 1.0
 */
public class InitFactory {

    public static AbsSinkNode createSinkNode(NodeDO nodeDO) {
        BizNodeType bizNodeType=createBizNodeType(nodeDO.getBizNodeType());
        SubType subType = bizNodeType.getSubType();
        switch (subType) {
            case NORMAL_SINK:
                return new NormalSinkNode(nodeDO.getId(), nodeDO.getDescriptionProps());
            default:
                throw new DataError("Sink node type error,and the sub type is" + subType);
        }
    }

    public static AbsSourceNode createSourceNode(NodeDO nodeDO) {
        BizNodeType bizNodeType=createBizNodeType(nodeDO.getBizNodeType());
        SubType subType = bizNodeType.getSubType();
        switch (subType) {
            case CONST_SOURCE:
                Object v = nodeDO.getParams().get("value");
                return new ConstSourceNode(nodeDO.getId(), (int) v, nodeDO.getDescriptionProps());
            default:
                throw new DataError("Source node type error,and the sub type is" + subType);
        }
    }

    public static ProcessNode createProcessNode(NodeDO nodeDO) {
        BizNodeType bizNodeType=createBizNodeType(nodeDO.getBizNodeType());
        SubType subType = bizNodeType.getSubType();
        switch (subType) {
            case GAIN_PROCESS:
                Object v = nodeDO.getParams().get("param");
                return new GainProcessNode(nodeDO.getId(), (int) v, nodeDO.getDescriptionProps());
            default:
                throw new DataError("Source node type error,and the sub type is" + subType);
        }
    }

    public static Node crateNode(NodeDO nodeDO) {
        BizNodeType bizNodeType=createBizNodeType(nodeDO.getBizNodeType());
        switch (bizNodeType.getBasicType()) {
            case SOURCE:
                return createSourceNode(nodeDO);
            case SINK:
                return createSinkNode(nodeDO);
            case PROCESS:
                return createProcessNode(nodeDO);
        }
        return null;
    }

    public static Stream createStream(StreamDO streamDO) {
        Stream stream = new Stream(streamDO.getId());
        List<NodeDO> nodeDOList = streamDO.getNodeList();
        if (nodeDOList.size() < 2) {
            return stream;
        }
        stream.addSource(createSourceNode(nodeDOList.get(0)));
        stream.addSink(createSinkNode(nodeDOList.get(nodeDOList.size() - 1)));
        for (int i = 1; i < nodeDOList.size() - 1; i++) {
            stream.pushNode(createProcessNode(nodeDOList.get(i)));
        }
        return stream;
    }

    public static Page createPage(PageDO pageDO) {
//        List<Stream> streams = new ArrayList<>();
//        for (StreamDO streamDO : pageDO.getStreamList()) {
//            streams.add(createStream(streamDO));
//        }
        Map<String, Node> map = new HashMap<>();
        for (NodeDO nodeDO : pageDO.getNodeList()) {
//            streams.add(createStream(streamDO));
            map.put(nodeDO.getId(), crateNode(nodeDO));
        }
        return Page.createPage(pageDO.getId(), map, pageDO.getConfig());
    }

    public static BizNodeType createBizNodeType(BizTypeDO bizTypeDO) {
        return new BizNodeType(BasicType.create(bizTypeDO.getBasicType()), SubType.create(bizTypeDO.getSubType()));
    }
}
