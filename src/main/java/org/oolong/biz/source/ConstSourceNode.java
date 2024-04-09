package org.oolong.biz.source;

import org.oolong.entity.basic.BasicType;
import org.oolong.entity.basic.SubType;
import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;
import org.oolong.entity.props.DescriptionProps;
import org.oolong.entity.serializable.NodeDO;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:25
 * @Version 1.0
 */
public class ConstSourceNode extends AbsSourceNode {
    int value;

    public ConstSourceNode(String id, int value, DescriptionProps descriptionProps){
        this(value);
        this.id=id;
        this.descriptionProps=descriptionProps;
    }

    public ConstSourceNode(int value){
        this.value=value;
        this.nodeType.setType(BasicType.SOURCE, SubType.CONST_SOURCE);
    }


    @Override
    public int output() {
        return value;
    }

    @Override
    public void process(RunConfig config, RunContext ctx) {

    }

    @Override
    public NodeDO toNodeDO() {
        NodeDO nodeDO= this.convertToData();
        nodeDO.setParam("value",value);
        return nodeDO;
    }
}
