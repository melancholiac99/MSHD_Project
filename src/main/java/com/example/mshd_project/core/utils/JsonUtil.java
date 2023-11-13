package com.example.mshd_project.core.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mshd_project.core.entity.CodeShow;

/**
 * Json工具类，只适用于存放codeShow类型数据的Json...
 * @author zyt
 * @date 2023/11/13
 */

public class JsonUtil {
    public static CodeShow parseJson(JSONObject jobj){
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

        return codeShow;
    }
}
