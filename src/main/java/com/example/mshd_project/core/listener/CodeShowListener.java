package com.example.mshd_project.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.exception.ExcelDataConvertException;
import com.alibaba.fastjson.JSON;
import com.example.mshd_project.core.domain.CodeShow;
import com.alibaba.excel.util.ListUtils;
import com.example.mshd_project.core.service.Callback;
import com.example.mshd_project.core.service.CodeUploadService;
import com.example.mshd_project.core.utils.Result;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.example.mshd_project.core.config.Constants.BATCH_COUNT;



/**
 * @author zyt
 */
   @Slf4j
public class CodeShowListener extends AnalysisEventListener<CodeShow>  {
    private List<CodeShow> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
    private CodeUploadService codeUploadService;
    private Callback uploadCallBack;

    public CodeShowListener(CodeUploadService codeUploadService,Callback uploadCallBack) {
        this.uploadCallBack = uploadCallBack;
        this.codeUploadService = codeUploadService;
    }

    @Override
    public void invoke(CodeShow codeShow, AnalysisContext analysisContext) {
        //log.info("解析到一条数据:{}", JSON.toJSONString(codeShow));
        cachedDataList.add(codeShow);

        if (cachedDataList.size() >= BATCH_COUNT) {
            log.info("{}条数据，开始存储数据库！", cachedDataList.size());
           Result uploadExcelResult = codeUploadService.getExcelData(cachedDataList);
//           if(uploadExcelResult.getResultCode() == 500){
//               uploadCallBack.onUploadResult(uploadExcelResult);
//           }
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }

    }
    @Override
    public void onException(Exception exception, AnalysisContext context) {
        log.error("解析失败，但是继续解析下一行:{}", exception.getMessage());
        // 如果是某一个单元格的转换异常 能获取到具体行号
        // 如果要获取头的信息 配合invokeHeadMap使用
        if (exception instanceof ExcelDataConvertException) {
            ExcelDataConvertException excelDataConvertException = (ExcelDataConvertException)exception;
            log.error("第{}行，第{}列解析异常，数据为:{}", excelDataConvertException.getRowIndex(),
                    excelDataConvertException.getColumnIndex(), excelDataConvertException.getCellData());
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        Result uploadExcelResult = codeUploadService.getExcelData(cachedDataList);
        uploadCallBack.onUploadResult(uploadExcelResult);
        log.info("所有数据存储完成！");
    }
}
