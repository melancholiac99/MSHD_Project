package com.example.mshd_project.core.service.impl;

import com.example.mshd_project.core.dao.CodeShowMapper;
import com.example.mshd_project.core.entity.CodeShow;
import com.example.mshd_project.core.service.CodeShowService;
import com.example.mshd_project.core.utils.PageQueryUtil;
import com.example.mshd_project.core.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CodeShowServiceImpl implements CodeShowService {
    @Autowired
    private CodeShowMapper codeShowMapper;

    @Override//实现接口方法，不要忘记加Override注解！
    public PageResult getCodePage(PageQueryUtil pageUtil){
        List<CodeShow> codeShowList = codeShowMapper.findCodeList(pageUtil);
        int total = codeShowMapper.getTotalCodes(pageUtil);
        PageResult pageResult = new PageResult(codeShowList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
}
