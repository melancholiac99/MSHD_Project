package com.example.mshd_project.core.service.impl;

import com.example.mshd_project.core.dao.CodeShowMapper;
import com.example.mshd_project.core.domain.CodeShow;
import com.example.mshd_project.core.service.CodeShowService;
import com.example.mshd_project.core.utils.PageQueryUtil;
import com.example.mshd_project.core.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zyt
 */
@Service
public class CodeShowServiceImpl implements CodeShowService {
    @Autowired
    private CodeShowMapper codeShowMapper;

    @Override
    //实现接口方法，不要忘记加Override注解！
    public PageResult getCodePage(PageQueryUtil pageUtil){
        List<CodeShow> codeShowList = codeShowMapper.findCodeList(pageUtil);
        int total = codeShowMapper.getTotalCodes(pageUtil);
        PageResult pageResult = new PageResult(codeShowList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }

    @Override
    public Boolean deleteBatch(String[] ids) {
        return codeShowMapper.deleteBatch(ids) > 0;
    }

    @Override
    public String saveCode(CodeShow codeShow) {
//
//        BlogCategory blogCategory = categoryMapper.selectByPrimaryKey(blog.getBlogCategoryId());
//        if (blogCategory == null) {
//            blog.setBlogCategoryId(0);
//            blog.setBlogCategoryName("默认分类");
//        } else {
//            //设置博客分类名称
//            blog.setBlogCategoryName(blogCategory.getCategoryName());
//            //分类的排序值加1
//            blogCategory.setCategoryRank(blogCategory.getCategoryRank() + 1);
//        }
        //保存文章
        if (codeShowMapper.insert(codeShow) == 1) {
            //新增标签数据并修改分类排序值
//            if (!CollectionUtils.isEmpty(tagListForInsert)) {
//                tagMapper.batchInsertBlogTag(tagListForInsert);
//            }
//            if (blogCategory != null) {
//                categoryMapper.updateByPrimaryKeySelective(blogCategory);
//            }
            return "success";
        }
        return "保存失败";
    }

    @Override
    public PageResult getCodesPageBySearch(String keyword, Integer page) {
            Map param = new HashMap();
            param.put("page", page);
            param.put("limit", 9);
            param.put("keyword", keyword);
            //过滤未审核的数据
            param.put("codeStatus", 1);
            PageQueryUtil pageUtil = new PageQueryUtil(param);
            List<CodeShow> codeShowList = codeShowMapper.findCodeList(pageUtil);
            int total = codeShowMapper.getTotalCodes(pageUtil);
            PageResult pageResult = new PageResult(codeShowList, total, pageUtil.getLimit(), pageUtil.getPage());
            return pageResult;
    }


}
