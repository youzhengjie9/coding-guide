package com.coding.guide.common.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;


/**
 * visible转换器
 * 0显示 1隐藏
 * @author youzhengjie
 * @date 2022/10/23 23:18:41
 */
public class VisibleConverter implements Converter<Integer> {

    private static final String SHOW="显示";

    private static final String HIDE="隐藏";


    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }


    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }


    /**
     * excel表的visible转换为java类中的visible
     *
     * @param cellData            单元格数据
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置
     * @return {@link Integer}
     * @throws Exception 异常
     */
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();
        return (stringValue.equals(SHOW)) ? 0 : 1  ;
    }


    /**
     * java类中的visible转换为excel表中的visible
     *
     * @param value               价值
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置
     * @return {@link WriteCellData}<{@link ?}>
     * @throws Exception 异常
     */
    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        if(value == 0){
            return new WriteCellData<>(SHOW);
        }else {
            return new WriteCellData<>(HIDE);
        }
    }
}
