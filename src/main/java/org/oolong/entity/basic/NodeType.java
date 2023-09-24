package org.oolong.entity.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NodeType {
    BasicType basicType;
    SubType subType;

    public NodeType(BasicType basicType,SubType subType){
        this.basicType=basicType;
        this.subType=subType;
    }

    public void setType(BasicType basicType,SubType subType){
        this.basicType=basicType;
        this.subType=subType;
    }
    @Override
    public String toString() {
        return "NodeType{" +
                "basicType=" + basicType +
                ", subType=" + subType +
                '}';
    }
}
