package org.oolong.entity.basic;

import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;
import org.oolong.entity.props.DescriptionProps;
import org.oolong.entity.serializable.NodeDO;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:15
 * @Version 1.0
 */
public abstract class Node extends Unit implements IProcessor {

    static String PREFIX = "node-";
    static int num = 0;

    protected BizNodeType nodeType;

    protected DescriptionProps descriptionProps;

    public Node() {
        super(PREFIX + num);
        num++;
        this.nodeType=new BizNodeType(BasicType.UNKNOWN,SubType.UNKNOWN);
    }

    @Override
    public void start(RunConfig config, RunContext ctx) {
    }

    @Override
    public void stop(RunConfig config, RunContext ctx) {
    }

    public abstract NodeDO toNodeDO();

    protected NodeDO convertToData(){
        return new NodeDO(this.id,this.nodeType,this.descriptionProps);
    }
}
