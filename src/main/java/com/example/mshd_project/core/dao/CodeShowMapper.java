package com.example.mshd_project.core.dao;

import com.example.mshd_project.core.entity.CodeShow;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface CodeShowMapper {

       //查找所有Code，这里将Map作为参数传入
       List<CodeShow> findCodeList(Map map);
       int getTotalCodes(Map map);
}
