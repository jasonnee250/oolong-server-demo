package org.oolong.entity.doc;

import lombok.Getter;
import lombok.Setter;
import org.oolong.entity.basic.Node;
import org.oolong.entity.basic.Unit;
import org.oolong.entity.config.RunConfig;
import org.oolong.entity.serializable.PageDO;
import org.oolong.entity.stream.Stream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: J.N
 * @Date 2023/9/20 21:37
 * @Version 1.0
 */

@Getter
public class Page extends Unit {

    static String PREFIX = "page-";
    static int num = 0;

    Map<String, Node> nodeMap;
    List<Stream> streamList;

    RunConfig config;

    public Page() {
        super(PREFIX + num);
        num++;
        streamList=new ArrayList<>();
    }

    public Page(String id) {
        this();
        this.id = id;
    }

    public static Page createPage(Map<String,Node> nodeMap, RunConfig config) {
        Page page = new Page();
        page.config = config;
        page.nodeMap = nodeMap;
        return page;
    }

    public static Page createPage(String id, Map<String,Node> nodeMap, RunConfig config) {
        Page page = new Page(id);
        page.config = config;
        page.nodeMap = nodeMap;
        return page;
    }

    public PageDO toPageDO() {
        PageDO pageDO = new PageDO(this.id,config);
        for(Map.Entry<String,Node> entry:this.nodeMap.entrySet()){
            pageDO.getNodeList().add(entry.getValue().toNodeDO());
        }
//        for (Stream stream : streamList) {
//            pageDO.addStreamDO(stream.toStreamDO());
//        }
        return pageDO;
    }
}
