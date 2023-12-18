package com.example.mshd_project.core.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
/**
 * @author zyt
 * @date 23/12/18
 */

public class CodeShow {
    /** 灾情码 */
    @ExcelProperty(index = 1)
    private String codeId;
    /** 所在地 省名 */
   @ExcelProperty(index = 2)
    private String province;
    /** 所在地 市名 */
    @ExcelProperty(index = 3)
    private String PL_city;
    /** 所在地 区县名 */
    @ExcelProperty(index = 4)
    private String district;
    /** 乡镇 */
    @ExcelProperty(index = 5)
    private String town;
    @ExcelProperty(index = 6)
    private String community;//社区
    @ExcelProperty(index = 14)
    private String userName;//用户名
    @ExcelProperty(index = 8)
    private String source;  // 来源
    @ExcelProperty(index =10)
    private String supporter; //载体(媒介)
    @ExcelProperty(index =15)
    private String disasterInfo;//震情信息(暂且使用String类型)
    @ExcelProperty(index = 16)
    private Byte isFile;//是否有文件
    @ExcelProperty(index = 21)
    private Byte isDeleted;//是否被删除
    @ExcelIgnore
    private Byte codeStatus;//代码是否被审核


//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPL_city() {
        return PL_city;
    }

    public void setPL_city(String PL_city) {
        this.PL_city = PL_city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSupporter() {
        return supporter;
    }

    public void setSupporter(String supporter) {
        this.supporter = supporter;
    }

    public String getDisasterInfo() {
        return disasterInfo;
    }

    public void setDisasterInfo(String disasterInfo) {
        this.disasterInfo = disasterInfo;
    }

    public Byte getIsFile() {
        return isFile;
    }

    public void setIsFile(Byte isFile) {
        this.isFile = isFile;
    }

    public Byte getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Byte getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(Byte codeStatus) {
        this.codeStatus = codeStatus;
    }
}
