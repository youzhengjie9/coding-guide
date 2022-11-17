package com.coding.guide.common.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;


/**
 * 菜单类型转换器
 * 0：目录; 1：菜单; 2：按钮
 * @author youzhengjie
 * @date 2022/10/23 23:18:41
 */
public class MenuTypeConverter implements Converter<Integer> {

    private static final String DIR="目录";

    private static final String MENU="菜单";

    private static final String BUTTON="按钮";


    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }


    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }


    /**
     * excel表的type转换为java类中的type
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
        if(stringValue.equals(DIR)){
            return 0;
        }else if(stringValue.equals(MENU)){
            return 1;
        }else {
            return 2;
        }
    }

    /**
     * java类中的type转换为excel表中的type
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
            return new WriteCellData<>(DIR);
        }else if(value == 1) {
            return new WriteCellData<>(MENU);
        }else {
            return new WriteCellData<>(BUTTON);
        }
    }
}
