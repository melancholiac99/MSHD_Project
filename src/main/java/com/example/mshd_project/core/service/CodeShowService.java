package com.example.mshd_project.core.service;

import com.example.mshd_project.core.utils.PageQueryUtil;
import com.example.mshd_project.core.utils.PageResult;

public interface CodeShowService {
    PageResult getCodePage(PageQueryUtil pageUtil);

}
