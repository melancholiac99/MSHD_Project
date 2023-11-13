package com.example.mshd_project.core.controller;

import com.alibaba.fastjson.JSONException;
import com.example.mshd_project.core.service.CodeUploadService;
import com.example.mshd_project.core.utils.Result;
import com.example.mshd_project.core.utils.ResultGenerator;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static com.example.mshd_project.core.config.Constants.FILE_DOWNLOAD_DIC;

/**
 * @author zyt
 */
@RestController
@RequestMapping("/upload")
public class CodeUploadController {
    @Resource
    CodeUploadService codeUploadService;
    @PostMapping("/uploadjsonfile")
    @CrossOrigin
    public Result uploadJsonFile(@RequestParam(value = "jsonFile") MultipartFile multipartFile) throws IOException {
    //判断文件是否为空
        if (multipartFile.isEmpty()) {
            return ResultGenerator.genFailResult("文件为空！");
        }
        try {
            //清理文件名，防止路径注入攻击
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            // 将文件保存到指定位置
            Files.copy(multipartFile.getInputStream(), Paths.get(FILE_DOWNLOAD_DIC, fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("IOException 咱也不知道是啥，好像是文件保存失败");
        }
        return codeUploadService.getJsonData(multipartFile);
}

    @PostMapping("/uploadXmlFiles")
    @CrossOrigin
    public Object uploadXmlFiles(@RequestParam(value = "xmlFile") MultipartFile multipartFile) throws JSONException, IOException {
        //判断文件是否为空
        if (multipartFile.isEmpty()) {
            return ResultGenerator.genFailResult("文件为空！");
        }
        try {
            //清理文件名，防止路径注入攻击
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            // 将文件保存到指定位置
            Files.copy(multipartFile.getInputStream(), Paths.get(FILE_DOWNLOAD_DIC, fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("IOException 咱也不知道是啥，好像是文件保存失败");
        }
        return codeUploadService.getXmlData(multipartFile);
    }

}
