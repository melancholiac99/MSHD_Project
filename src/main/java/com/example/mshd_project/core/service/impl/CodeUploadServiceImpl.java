package com.example.mshd_project.core.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mshd_project.core.dao.CodeShowMapper;
import com.example.mshd_project.core.entity.CodeShow;
import com.example.mshd_project.core.service.CodeUploadService;
import com.example.mshd_project.core.utils.Result;
import com.example.mshd_project.core.utils.ResultGenerator;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class CodeUploadServiceImpl implements CodeUploadService {
    @Autowired
    CodeShowMapper mapper;

    @Override
    public Result getJsonData(MultipartFile multipartFile) throws IOException {

//   先将multipartFile类型转化为File，选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], filename[1] + ".");
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
            return ResultGenerator.genFailResult("IOException"+ e.getMessage());
        }
        //数据读取
        String json = FileUtils.readFileToString(file);
        String JsonContextArr="["+json+"]";
        //String字符串转换为Json数组
        JSONArray jsonArray = JSON.parseArray(JsonContextArr);
        //遍历每一个json对象，将内容存放到CodeShow对象中
        for (Object obj : jsonArray) {
            JSONObject jobj = (JSONObject) obj;
            //少用强制类型转换！
            Long codeId = jobj.getLong("codeId");
            String province = jobj.getString("province");
            String PL_city = jobj.getString("PL_city");
            String district = jobj.getString("district");
            String town = jobj.getString("town");
            String community = jobj.getString("community");
            String userName = jobj.getString("userName");
            String source = jobj.getString("source");
            String supporter = jobj.getString("supporter");
            String disasterInfo = jobj.getString("disasterInfo");
            Byte isFile = jobj.getByte("isFile");
            Byte isDeleted = jobj.getByte("isDeleted");
            Byte codeStatus = jobj.getByte("codeStatus");
            //给codeShow对象属性赋值
            CodeShow codeShow = new CodeShow();
            codeShow.setCodeId(codeId);
            codeShow.setProvince(province);
            codeShow.setPL_city(PL_city);
            codeShow.setDistrict(district);
            codeShow.setTown(town);
            codeShow.setCommunity(community);
            codeShow.setUserName(userName);
            codeShow.setSupporter(supporter);
            codeShow.setDisasterInfo(disasterInfo);
            codeShow.setSource(source);
            codeShow.setIsFile(isFile);
            codeShow.setIsDeleted(isDeleted);
            codeShow.setCodeStatus(codeStatus);

            //数据插入
            if (mapper.insert(codeShow) != 1) {
                return ResultGenerator.genFailResult("数据插入失败！");
            }
        }
        return ResultGenerator.genSuccessResult("数据插入成功！");
    }
}
