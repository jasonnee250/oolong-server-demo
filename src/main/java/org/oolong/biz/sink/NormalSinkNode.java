package org.oolong.biz.sink;

import org.oolong.core.TValue;
import org.oolong.entity.basic.BasicType;
import org.oolong.entity.basic.SubType;
import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;
import org.oolong.entity.props.DescriptionProps;
import org.oolong.entity.serializable.NodeDO;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:30
 * @Version 1.0
 */
public class NormalSinkNode extends AbsSinkNode{

    private int value;

    public NormalSinkNode(){
        this.nodeType.setType(BasicType.SINK, SubType.NORMAL_SINK);
    }

    public NormalSinkNode(String id, DescriptionProps descriptionProps){
        this();
        this.id=id;
        this.descriptionProps=descriptionProps;
    }
    @Override
    public void input(int value) {
        this.value=value;
        this.nodeType.setType(BasicType.SINK, SubType.NORMAL_SINK);
    }

    @Override
    public void process(RunConfig config, RunContext ctx) {
        ctx.getRunResult().tValueList.add(new TValue(ctx.currentTime,this.value));
    }

    @Override
    public NodeDO toNodeDO() {
        NodeDO nodeDO= this.convertToData();
        return nodeDO;
    }
}
