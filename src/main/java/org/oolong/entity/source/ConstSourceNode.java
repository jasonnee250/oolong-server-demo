package org.oolong.entity.source;

import org.oolong.entity.basic.Node;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:25
 * @Version 1.0
 */
public class ConstSourceNode extends AbsSourceNode {
    int value;

    public ConstSourceNode(int value){
        this.value=value;
    }


    @Override
    public int output() {
        return value;
    }

    @Override
    public void process() {

    }
}
