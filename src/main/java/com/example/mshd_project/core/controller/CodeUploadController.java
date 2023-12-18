package com.example.mshd_project.core.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONException;
import com.example.mshd_project.core.domain.CodeShow;
import com.example.mshd_project.core.listener.CodeShowListener;
import com.example.mshd_project.core.service.Callback;
import com.example.mshd_project.core.service.CodeUploadService;
import com.example.mshd_project.core.utils.Result;
import com.example.mshd_project.core.utils.ResultGenerator;
import jakarta.annotation.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.atomic.AtomicReference;

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
            return ResultGenerator.genFailResult("IOException, 好像是文件保存失败"+ e.getMessage());
        }
        return codeUploadService.getJsonData(multipartFile);
}

    @PostMapping("/uploadxmlfile")
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
            return ResultGenerator.genFailResult("IOException, 好像是文件保存失败"+ e.getMessage());
        }
        return codeUploadService.getXmlData(multipartFile);
    }

    @PostMapping("/uploadexcelfile")
    @CrossOrigin
    public Result uploadExcelFile(@RequestParam(value = "excelFile") MultipartFile multipartFile) throws IOException {
        //原子引用类，可以线程安全地更新对象的引用
        final AtomicReference<Result> uploadResult = new AtomicReference<>();
        Callback uploadCallback = new Callback() {
            @Override
            public void onUploadResult(Result result) {
                // 处理上传结果，可以在这里返回到前端或进行其他操作
                uploadResult.set(result);
            }
        };
        if (multipartFile.isEmpty()) {
            return ResultGenerator.genFailResult("文件为空！");
        }
        //获取文件流
        InputStream inputFileStream = null;
        try {
            //清理文件名，防止路径注入攻击
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            // 将文件保存到指定位置
            inputFileStream = multipartFile.getInputStream();
            Files.copy(inputFileStream, Paths.get(FILE_DOWNLOAD_DIC, fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("IOException, 好像是文件保存失败"+ e.getMessage());
        }
//        try {
            EasyExcel.read(inputFileStream = multipartFile.getInputStream(), CodeShow.class, new CodeShowListener(codeUploadService, uploadCallback))
                    .sheet("模板")
                    .doRead();
//        } catch(Exception exception){
//            return ResultGenerator.genFailResult("EasyExcel读取出现异常！");
//        }
//        }
        return uploadResult.get();
    }

}
