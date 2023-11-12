package com.example.mshd_project.core.entity;

import lombok.Data;

@Data
public class CodeShow {
    private Long codeId;//主键
    private String province;//省
    private String PL_city;//地级城市
    private String district;//区
    private String town;//乡镇
    private String community;//社区
    private String userName;//用户名
    private String source;  // 来源
    private String supporter; //载体(媒介)

    private String disasterInfo;//震情信息(暂且使用String类型)

    private Byte isFile;//是否有文件
    private Byte isDeleted;//是否被删除

    private Byte codeStatus;//代码是否被审核

}
