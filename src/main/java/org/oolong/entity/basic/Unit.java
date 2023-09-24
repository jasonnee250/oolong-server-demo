package org.oolong.entity.basic;

import lombok.Getter;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:13
 * @Version 1.0
 */
public abstract class Unit {
    @Getter
    protected String id;

    public Unit(String id){
        this.id=id;
    }
}
