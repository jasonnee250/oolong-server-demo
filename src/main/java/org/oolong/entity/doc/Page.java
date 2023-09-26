package org.oolong.entity.doc;

import lombok.Getter;
import lombok.Setter;
import org.oolong.entity.basic.Unit;
import org.oolong.entity.config.RunConfig;
import org.oolong.entity.serializable.PageDO;
import org.oolong.entity.stream.Stream;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: J.N
 * @Date 2023/9/20 21:37
 * @Version 1.0
 */

@Getter
public class Page extends Unit {

    static String PREFIX = "page-";
    static int num = 0;

    List<Stream> streamList;

    RunConfig config;

    public Page() {
        super(PREFIX + num);
        num++;
    }

    public Page(String id) {
        this();
        this.id = id;
    }

    public static Page createPage(List<Stream> streamList, RunConfig config) {
        Page page = new Page();
        page.config = config;
        page.streamList = streamList;
        return page;
    }

    public static Page createPage(String id, List<Stream> streamList, RunConfig config) {
        Page page = new Page(id);
        page.config = config;
        page.streamList = streamList;
        return page;
    }

    public PageDO toPageDO() {
        PageDO pageDO = new PageDO(this.id,config);
        for (Stream stream : streamList) {
            pageDO.addStreamDO(stream.toStreamDO());
        }
        return pageDO;
    }
}
