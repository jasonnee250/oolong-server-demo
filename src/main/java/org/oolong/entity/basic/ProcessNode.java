package org.oolong.entity.basic;

import org.oolong.entity.props.DescriptionProps;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:48
 * @Version 1.0
 */
public abstract class ProcessNode extends Node implements IInput, IOutput {

    public ProcessNode() {
    }

    public ProcessNode(String id, DescriptionProps descriptionProps) {
        this.id = id;
        this.descriptionProps = descriptionProps;
    }
}
