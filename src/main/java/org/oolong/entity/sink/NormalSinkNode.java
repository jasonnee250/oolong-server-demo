package org.oolong.entity.sink;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:30
 * @Version 1.0
 */
public class NormalSinkNode extends AbsSinkNode{
    @Override
    public void input(int value) {
        System.out.println("----->out: "+value);
    }

    @Override
    public void process() {

    }
}
