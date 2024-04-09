package org.oolong.storage.local.read;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.oolong.entity.doc.Page;
import org.oolong.entity.serializable.PageDO;

import java.io.*;

/**
 * @Author: J.N
 * @Date 2023/9/20 22:48
 * @Version 1.0
 */
public class FileReader {

    InputStream inputStream;
    BufferedInputStream bufferedInputStream;

    public FileReader(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        inputStream = new FileInputStream(file);
        bufferedInputStream = new BufferedInputStream(inputStream);

    }

    public PageDO readPage() throws IOException {
        byte[] buffer=new byte[30*1024];
        bufferedInputStream.read(buffer);
        PageDO page=JSON.parseObject(buffer, PageDO.class);
        return page;
    }

    public void end() throws IOException {
        this.bufferedInputStream.close();
        this.inputStream.close();
    }


}
