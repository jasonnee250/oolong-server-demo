package org.oolong.biz.math;

import com.alibaba.fastjson2.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;
import org.oolong.entity.basic.BasicType;
import org.oolong.entity.basic.ProcessNode;
import org.oolong.entity.basic.SubType;
import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;
import org.oolong.entity.serializable.NodeDO;

/**
 * @Author: J.N
 * @Date 2023/9/19 23:46
 * @Version 1.0
 */
public class GainProcessNode extends ProcessNode {
    /**
     * 比例系数
     */
    @Getter
    private final int param;
    /**
     * 内部缓存值
     */
    private int value;

    public GainProcessNode(int param){
        this.param=param;
        this.nodeType.setType(BasicType.PROCESS,SubType.GAIN_PROCESS);
    }
    @Override
    public void input(int value) {
        this.value=value;
    }

    @Override
    public int output() {
        return this.value;
    }

    @Override
    public void process(RunConfig config, RunContext ctx) {
        this.value=this.value*this.param;
    }

    @Override
    public NodeDO toNodeDO() {
        NodeDO nodeDO=this.convertToData();
        nodeDO.setParam("param",param);
        return nodeDO;
    }
}
