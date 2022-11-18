package com.coding.guide.common.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson2.JSON;
import com.coding.guide.common.converter.LocalDateConverter;
import com.coding.guide.common.converter.LocalDateTimeConverter;
import com.coding.guide.common.data.ResponseResult;
import com.coding.guide.common.enums.ResponseType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * 二次封装easy-excel的工具类
 *
 * @author youzhengjie
 * @date 2022/10/22 11:47:56
 */
@Component
@Slf4j
public class EasyExcelUtil {

    /**
     * 封装写excel方法
     *
     * @param data            数据的List集合
     * @param dataEntityClass 数据的List集合对应的实体类。例如传过来的data是一个List<User>类型的集合，那dataEntityClass就要填User.class
     * @param fileNamePrefix   生成的excel文件名前缀
     * @param response        HttpServletResponse对象
     */
    public <T> void writeExcel(List<T> data, Class<?> dataEntityClass, String fileNamePrefix,HttpServletResponse response){
        try {
            //生成不重复的文件名。fileName是下载后的文件名，其实就是把传过来的fileNamePrefix后面加上时间字符串（防止下载的文件名重复）
            String dateStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String fileName = fileNamePrefix+dateStr;
            //防止下载时中文乱码
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileNamePrefix+".xlsx");
            response.setHeader("Access-Control-Expose-Headers","Content-disposition");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            //正式开始写excel
            EasyExcel.write(response.getOutputStream(), dataEntityClass)
                    .autoCloseStream(Boolean.FALSE).sheet(fileName)
                    .registerConverter(new LocalDateConverter())
                    .registerConverter(new LocalDateTimeConverter())
                    .doWrite(data);
        } catch (Exception e) {
            log.error("文件导出失败,错误信息{}",e.getMessage());
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            ResponseResult<Object> responseResult = new ResponseResult<>();
            responseResult.setCode(ResponseType.EXPORT_EXCEL_ERROR.getCode());
            responseResult.setMsg(ResponseType.EXPORT_EXCEL_ERROR.getMessage());
            try {
                response.getWriter().println(JSON.toJSONString(responseResult));
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }
}
