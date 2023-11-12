package com.example.mshd_project.core.dao;

import com.example.mshd_project.core.entity.CodeShow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface CodeShowMapper {

       /**查找所有Code，这里将Map作为参数传入，返回值是codeShow类型的list**/
       List<CodeShow> findCodeList(Map map);
       int getTotalCodes(Map map);
       /**将数据库中插入数据**/
       int insert(CodeShow codeShow);

}
