package com.example.mshd_project.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.example.mshd_project.core.dao.CodeShowMapper;
import com.example.mshd_project.core.service.CodeUploadService;
import com.example.mshd_project.core.utils.*;
import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.json.XML;

import java.io.File;
import java.io.IOException;

import static com.example.mshd_project.core.config.Constants.FILE_DOWNLOAD_DIC;

@Service
public class CodeUploadServiceImpl implements CodeUploadService {
    @Autowired
    CodeShowMapper mapper;

    @Override
    public Result getJsonData(MultipartFile multipartFile) throws IOException {
        //类型转换
        File file = MultipartFileToFileUtil.multipartFileToFile(multipartFile);
        //数据读取
        String json = FileUtils.readFileToString(file);
        String jsonContextArr="["+json+"]";
        //String字符串转换为Json数组
        JSONArray jsonArray = JSON.parseArray(jsonContextArr);
        //遍历每一个json对象，调用parseJson工具方法。
        for (Object obj : jsonArray) {
            JSONObject jobj = (JSONObject) obj;
            //数据插入
            if (mapper.insert(JsonUtil.parseJson(jobj)) != 1) {
                return ResultGenerator.genFailResult("数据插入失败！");
            }
        }
        return ResultGenerator.genSuccessResult("数据插入成功！");
    }
    @Override
    public Result getXmlData(MultipartFile multipartFile) throws IOException {
        try {
            File xmlFile = MultipartFileToFileUtil.multipartFileToFile(multipartFile);
            if (!xmlFile.exists()) {
                return ResultGenerator.genFailResult("XML文件不存在！");
            }
            SAXReader saxReader = new SAXReader();
            // 从文件流读入xml数据
            Document document;
            try {
                document = saxReader.read(xmlFile);
            } catch (DocumentException e) {
                e.printStackTrace();
                return ResultGenerator.genFailResult("读取XML文件失败：" + e.getMessage());
            }
            String xmlText = document.asXML().replace("<?xml version=\"1.0\" encoding=\"UTF-8\"?>","");
            org.json.JSONObject jsonObject = XML.toJSONObject(xmlText);
            jsonObject = (org.json.JSONObject) jsonObject.get(jsonObject.keys().next());
            String str = jsonObject.toString();
            String jsonContextArr="["+str+"]";
            //String字符串转换为Json数组
            JSONArray jsonArray = JSON.parseArray(jsonContextArr);
            // 遍历每一个json对象，调用parseJson工具方法。
            for (Object obj : jsonArray) {
                JSONObject jobj = (JSONObject) obj;
                // 数据插入
                if (mapper.insert(JsonUtil.parseJson(jobj)) != 1) {
                    return ResultGenerator.genFailResult("数据插入失败！insert返回不为1！");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("数据插入失败：" + e.getMessage());
        }

        return ResultGenerator.genSuccessResult("数据插入成功！");
    }
}
