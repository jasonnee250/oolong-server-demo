package org.oolong.entity;

import org.junit.Test;
import org.oolong.biz.initial.InitFactory;
import org.oolong.entity.config.RunConfig;
import org.oolong.entity.context.RunContext;
import org.oolong.entity.doc.Page;
import org.oolong.entity.serializable.PageDO;
import org.oolong.entity.stream.Stream;
import org.oolong.storage.local.read.FileReader;
import org.oolong.storage.local.write.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: J.N
 * @Date 2023/9/18 23:33
 * @Version 1.0
 */
public class TestSimpleStream {



    @Test
    public void testStream(){
        Stream stream=TestUtils.createStream();
        RunConfig config=new RunConfig(10,1);
        RunContext ctx=new RunContext();
        stream.run(config,ctx);
        System.out.println(ctx.getRunResult().toString());
    }

    @Test
    public void testWriteFile(){
        Stream stream=TestUtils.createStream();
        List<Stream> streams=new ArrayList<>();
        streams.add(stream);
        RunConfig config=new RunConfig(10,1);
        Page page=Page.createPage(streams,config);
        PageDO pageDO=page.toPageDO();
        try{
            FileWriter fileWriter=new FileWriter("/Users/nijiaxin/files/testFile"+".oolong");
            fileWriter.write(pageDO);
            fileWriter.end();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testReadFile(){
        try{
            FileReader reader=new FileReader("/Users/nijiaxin/files/testFile"+".oolong");
            PageDO pageDO=reader.readPage();
            Page page= InitFactory.createPage(pageDO);
            Stream stream=page.getStreamList().get(0);
            RunContext ctx=new RunContext();
            stream.run(page.getConfig(),ctx);
            System.out.println(ctx.getRunResult().toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
