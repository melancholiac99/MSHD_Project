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

 /**传入multipartFile类型数据，解析其中属性插入数据库，返回result**/
  Result getJsonData(MultipartFile file) throws IOException;
}
