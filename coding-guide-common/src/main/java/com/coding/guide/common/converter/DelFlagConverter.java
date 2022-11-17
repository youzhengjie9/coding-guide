package com.coding.guide.common.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * del_flag删除标记转换器。
 *
 * 作用是将private Integer delFlag;属性按照如下格式转换：
 * java类（Integer）     =>     excel表（String）
 * delFlag=0   =>     delFlag=未删除
 * delFlag=1   =>     delFlag=已删除
 *
 * @author youzhengjie
 * @date 2022/10/23 22:53:24
 */
public class DelFlagConverter implements Converter<Integer> {

    private static final String NOT_DEL="未删除";

    private static final String IS_DEL="已删除";

    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        String stringValue = cellData.getStringValue();

        if(stringValue.equals(NOT_DEL)){
            return 0;
        }else {
            return 1;
        }
    }

    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        String del;
        if(value == 0){
            del=NOT_DEL;
        }else {
            del=IS_DEL;
        }
        return new WriteCellData<>(del);
    }
}
