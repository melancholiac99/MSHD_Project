package com.example.mshd_project.core.service;

import com.example.mshd_project.core.domain.CodeShow;
import com.example.mshd_project.core.utils.PageQueryUtil;
import com.example.mshd_project.core.utils.PageResult;

public interface CodeShowService {
    /**
     * 获取指定数目以及分页大小的数据
     * @param pageUtil
     * @return PageResult
     */
    PageResult getCodePage(PageQueryUtil pageUtil);


    /**
     * @param ids
     * @return Boolean
     * 通过给定codeId数组删除数据
     * */
    Boolean deleteBatch(String[] ids);

    String saveCode(CodeShow codeShow);

    PageResult getCodesPageBySearch(String keyword, Integer page);
}
