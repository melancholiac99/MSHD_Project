package com.example.mshd_project.core.service;

import com.example.mshd_project.core.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * @author zyt
 */
public interface CodeUploadService {

 /**@param multipartFile
  * @return Result类型数据
  * 实现解析前端传来json文件，并将其中属性插入数据库表中
  * **/
  Result getJsonData(MultipartFile multipartFile) throws IOException;
  Result getXmlData(MultipartFile multipartFile) throws IOException;
}
