package com.example.mshd_project.core.config;

/**
 * 这是个常数类
 * @author zyt
 */
public class Constants {
    /**下载文件的默认url前缀**/
    public final static String FILE_DOWNLOAD_DIC = "src/main/resources/static";
    /** easyExcel 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收*/
    public static final int BATCH_COUNT = 5;
}
