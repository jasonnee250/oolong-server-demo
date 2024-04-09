package org.oolong.controller;

import org.oolong.entity.serializable.PageDO;
import org.oolong.storage.local.read.FileReader;
import org.oolong.storage.local.write.FileWriter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @Author: J.N
 * @Date 2023/9/29 23:40
 * @Version 1.0
 */
@Controller
public class FileController {

    @ResponseBody
    @PostMapping("/saveToFile")
    public String saveToFile(@RequestBody PageDO pageDO) {
        try {
            System.out.println(pageDO);
            FileWriter fileWriter = new FileWriter("/Users/nijiaxin/files/testFile" + ".oolong");
            fileWriter.write(pageDO);
            fileWriter.end();
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "failed";

    }

    @ResponseBody
    @GetMapping("/loadFile")
    public PageDO loadFile() {
        try {
            FileReader reader = new FileReader("/Users/nijiaxin/files/testFile" + ".oolong");
            PageDO pageDO = reader.readPage();
            System.out.println(pageDO.toString());
            return pageDO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
