package org.oolong.entity.basic;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:15
 * @Version 1.0
 */
public abstract class Node extends Unit implements IProcessor{

    static String PREFIX="node-";
    static int num=0;

    public Node(){
        super(PREFIX+num);
        num++;
    }
}
