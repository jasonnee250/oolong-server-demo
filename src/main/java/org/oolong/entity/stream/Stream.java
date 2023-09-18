package org.oolong.entity.stream;

import org.oolong.entity.basic.Node;
import org.oolong.entity.basic.ProcessNode;
import org.oolong.entity.basic.Unit;
import org.oolong.entity.sink.AbsSinkNode;
import org.oolong.entity.source.AbsSourceNode;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:13
 * @Version 1.0
 */
public class Stream extends Unit {

    static String PREFIX = "stream-";
    static int num = 0;

    protected AbsSourceNode source;
    protected AbsSinkNode sink;
    /**
     * 储存节点
     */
    LinkedList<ProcessNode> list=new LinkedList<>();


    public Stream() {
        super(PREFIX + num);
        num++;
    }

    public void addSource(AbsSourceNode source){
        this.source=source;
    }
    public void addSink(AbsSinkNode sink){
        this.sink=sink;
    }

    /**
     * 末尾添加节点
     *
     * @param node
     */
    public void pushNode(ProcessNode node) {
        list.addLast(node);
    }

    /**
     * stream尺寸
     *
     * @return
     */
    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void deleteLast() {
        list.removeLast();
    }

    public void run() {
        Iterator<ProcessNode> iterator = list.iterator();
        int value;
        value=source.output();
        while (iterator.hasNext()) {
            ProcessNode node=iterator.next();
            node.input(value);
            node.process();
            value=node.output();
        }
        sink.input(value);
    }
}