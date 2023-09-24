package org.oolong.storage.local.write;

import com.alibaba.fastjson2.JSON;
import org.oolong.entity.doc.Page;
import org.oolong.entity.serializable.PageDO;

import java.io.*;

/**
 * @Author: J.N
 * @Date 2023/9/20 21:23
 * @Version 1.0
 */
public class FileWriter {

    BufferedOutputStream bufferedOutputStream;
    OutputStream outputStream;

    public FileWriter(String path) throws IOException {

        File file=new File(path);
        if(!file.exists()){
            file.createNewFile();
        }
        outputStream=new FileOutputStream(file);
        bufferedOutputStream=new BufferedOutputStream(outputStream);
    }

    public void write(PageDO page) throws IOException {
        byte[] bytes=JSON.toJSONBytes(page);
        this.bufferedOutputStream.write(bytes);
    }


    public void end() throws IOException {
        this.bufferedOutputStream.close();
        this.outputStream.close();
    }
}
