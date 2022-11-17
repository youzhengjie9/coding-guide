package com.coding.guide.common.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * 用户状态转换器。实现Converter<Integer>接口，Converter泛型里面的Integer就是我们需要转换的java属性status的类型Integer
 *
 * 说白了就是把Integer类型的status属性，转换成String类型的excel属性
 *
 * 作用是将User类中的private Integer status属性按照如下格式转换：
 * java类（Integer）     =>     excel表（String）
 * status=0   =>     status=正常
 * status=1   =>     status=停用
 * @author youzhengjie
 * @date 2022/10/23 21:42:23
 */
public class StatusConverter implements Converter<Integer> {

    private static final String STATUS_OK="正常";

    private static final String STATUS_FAIL="停用";

    /**
     * 需要转换的java类型（也就是我们需要转换的status在java类中的类型，也就是Integer.class）
     * private Integer status
     * @return {@link Class}<{@link ?}>
     */
    @Override
    public Class<?> supportJavaTypeKey() {
        //指定java类中的status是Integer类型
        return Integer.class;
    }

    /**
     * 需要转换成的excel类型（我们上面目的就是把Integer类型的status属性，转换成String类型的excel属性）
     *
     * @return {@link CellDataTypeEnum}
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        //指定excel为String类型
        return CellDataTypeEnum.STRING;
    }

    /**
     * excel表的status转换为java类中的status
     *
     * @param cellData            单元格数据
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置
     * @return {@link Integer}
     * @throws Exception 异常
     */
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        //cellData.getStringValue()获取到的是excel表的status内容（比如：正常、停用）
        String stringValue = cellData.getStringValue();
        System.out.println(stringValue);
        return (stringValue.equals(STATUS_OK))? 0 : 1;
    }

    /**
     * java类中的status转换为excel表中的status
     *
     * @param value               value是java类中的status属性内容（比如0、1）
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置
     * @return {@link WriteCellData}<{@link ?}>
     * @throws Exception 异常
     */
    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        String statusName;
        //如果value=0,就将statusName设置为“正常”
        if(value == 0){
            statusName=STATUS_OK;
        }else { //反之就将statusName设置为“停用”
            statusName=STATUS_FAIL;
        }

        return new WriteCellData<>(statusName);
    }
}
