package com.example.mshd_project.core.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author zyt  11.12
 */

public class MultipartFileToFileUtil {

/**
 * @param multipartFile
 * @return
 * 将multipartFile类型转化为File，选择用缓冲区来实现这个转换即使用java创建的临时文件
 * 使用 MultipartFile.transferTo()方法 。
 *  @date 2023/11/13
 * **/
    public static File multipartFileToFile(MultipartFile multipartFile){
//
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], filename[1] + ".");
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
         return file;
    }


}
