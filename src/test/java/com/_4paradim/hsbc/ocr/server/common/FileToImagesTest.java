package com._4paradim.hsbc.ocr.server.common;

import com._4paradim.hsbc.ocr.server.common.utils.FileToImages;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileToImagesTest {

    @Test
    public void test1() throws Exception {
        File sourceFile = new File("/Users/4paradigm/Documents/tmp/表单.pdf");
        File destDirectory = new File("/Users/4paradigm/Documents/tmp/out/");
        FileUtils.cleanDirectory(destDirectory);
        List<File> files = FileToImages.toImages(sourceFile, destDirectory);
        System.out.println(files);
        //FileUtils.cleanDirectory(destDirectory);
        //FileUtils.deleteQuietly(destDirectory);
    }
}
