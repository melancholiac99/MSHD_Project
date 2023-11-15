package com.example.mshd_project.core.controller;

import com.example.mshd_project.core.entity.CodeShow;
import com.example.mshd_project.core.service.CodeShowService;
import com.example.mshd_project.core.utils.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/admin")

public class CodeShowController {
    @Resource
    private CodeShowService codeShowService;

    @CrossOrigin
    @GetMapping("/codeshow/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params){
        //检查请求参数是否合理，如果不合理返回result的message是"参数异常"
        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        //请求合理，则解析请求，将其转化为PageQueryUtil类的对象
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageResult codeShowPageResult = codeShowService.getCodePage(pageQueryUtil);
        if(codeShowPageResult.getList() != null && codeShowPageResult.getList().size()!=0){
            return ResultGenerator.genSuccessResult(codeShowPageResult);
        }
        else {
            return ResultGenerator.genFailResult("未找到数据，或者是返回数据列表没有初始化！");
        }
    }


    @CrossOrigin
    @PostMapping("/codeshow/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        if (ids.length < 1) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        if (codeShowService.deleteBatch(ids)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("删除失败");
        }
    }
    @CrossOrigin
    @PostMapping("/codeshow/add")
    @ResponseBody
    public Result save(@RequestParam("codeId") Long codeId,
                       @RequestParam("province") String province,
                       @RequestParam(name = "PL_city", required = false) String PL_city,
                       @RequestParam("district") String district,
                       @RequestParam("town") String town,
                       @RequestParam("community") String community,
                       @RequestParam("userName") String userName,
                       @RequestParam("source") String source,
                       @RequestParam("supporter") String supporter,
                           @RequestParam("disasterInfo") String disasterInfo,
                       @RequestParam("isFile") Byte isFile,
                       @RequestParam("isDeleted") Byte isDeleted,
                       @RequestParam("codeStatus") Byte codeStatus) {
        if (codeId == null) {
            return ResultGenerator.genFailResult("请输入灾情编码");
        }
        if (!StringUtils.hasText(district)) {
            return ResultGenerator.genFailResult("请输入区县信息");
        }
        if (town.trim().length() > 150) {
            return ResultGenerator.genFailResult("乡镇信息过长");
        }
        if (community.trim().length() > 150) {
            return ResultGenerator.genFailResult("社区信息过长");
        }
        if (!StringUtils.hasText(userName)) {
            return ResultGenerator.genFailResult("请输入用户名");
        }
        CodeShow codeShow = new CodeShow();
        codeShow.setCodeId(codeId);
        codeShow.setProvince(province);
        codeShow.setPL_city(PL_city);
        codeShow.setDistrict(district);
        codeShow.setTown(town);
        codeShow.setCommunity(community);
        codeShow.setUserName(userName);
        codeShow.setSource(source);
        codeShow.setSupporter(supporter);
        codeShow.setDisasterInfo(disasterInfo);
        codeShow.setIsFile(isFile);
        codeShow.setIsDeleted(isDeleted);
        codeShow.setCodeStatus(codeStatus);
        String saveBlogResult = codeShowService.saveCode(codeShow);
        if ("success".equals(saveBlogResult)) {
            return ResultGenerator.genSuccessResult("添加成功");
        } else {
            return ResultGenerator.genFailResult(saveBlogResult);
        }
    }

    /**
     * 搜索列表页
     *
     * @return
     */
    @CrossOrigin
    @GetMapping({"/codeshow/search/{keyword}/{page}"})
    @ResponseBody
    public Result search(@PathVariable("keyword") String keyword, @PathVariable("page") Integer page) {
        if(!PatternUtil.validNumKeyword(keyword)){
            return ResultGenerator.genFailResult("只能输入数字0~9");
        }
        if (page >0){
            PageResult codeShowPageResult = codeShowService.getCodesPageBySearch(keyword, page);
            //判断数据库返回数据列表是否为空
            if(codeShowPageResult.getList() != null && codeShowPageResult.getList().size()!=0){
                return ResultGenerator.genSuccessResult(codeShowPageResult);
            }
            else {
                return ResultGenerator.genFailResult("未找到符合要求的编码，或者是返回数据列表没有初始化！");
            }
        }
        else {
            return ResultGenerator.genFailResult("页码必须为正整数！");
        }
    }

}
