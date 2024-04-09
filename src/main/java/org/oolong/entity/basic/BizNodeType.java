package org.oolong.entity.basic;

import lombok.Getter;
import lombok.Setter;
import org.oolong.entity.serializable.BizTypeDO;

@Getter
@Setter
public class BizNodeType {
    BasicType basicType;
    SubType subType;

    public BizNodeType(){}

    public BizNodeType(BasicType basicType, SubType subType){
        this.basicType=basicType;
        this.subType=subType;
    }

    public void setType(BasicType basicType,SubType subType){
        this.basicType=basicType;
        this.subType=subType;
    }

    public BizTypeDO toDO(){
        return new BizTypeDO(this.basicType.toString(),this.subType.toString());
    }

    @Override
    public String toString() {
        return "NodeType{" +
                "basicType=" + basicType +
                ", subType=" + subType +
                '}';
    }
}
