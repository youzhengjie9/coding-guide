package com.coding.guide.common.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * 用户性别转换器。实现Converter<Integer>接口，Converter泛型里面的Integer就是我们需要转换的java属性sex的类型。
 *
 * 作用是将User类中的private Integer sex;属性按照如下格式转换：
 * java类（Integer）     =>     excel表（String）
 * sex=0   =>     sex=男
 * sex=1   =>     sex=女
 * sex=2   =>     sex=未知
 *
 * @author youzhengjie
 * @date 2022/10/23 22:22:55
 */
public class UserSexConverter implements Converter<Integer> {

    private static final String MALE="男";
    private static final String FEMALE="女";
    private static final String UNKNOWN="未知";

    /**
     * sex在Java类中的类型。Integer sex;
     * @return {@link Class}<{@link ?}>
     */
    @Override
    public Class<?> supportJavaTypeKey() {
        return Integer.class;
    }

    /**
     * sex在excel表中的类型（String）
     *
     * @return {@link CellDataTypeEnum}
     */
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    /**
     * excel表的sex转换为java类中的sex
     *
     * @param cellData            单元格数据
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置
     * @return {@link Integer}
     * @throws Exception 异常
     */
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        //cellData.getStringValue()获取到的是excel表的sex内容（比如：男、女、未知）
        String stringValue = cellData.getStringValue();
        //
        if(stringValue.equals(MALE)){
            return 0;
        }else if(stringValue.equals(FEMALE)){
            return 1;
        }else{
            return 2;
        }
    }

    /**
     * java类中的sex转换为excel表中的sex
     *
     * @param value               价值
     * @param contentProperty     内容属性
     * @param globalConfiguration 全局配置
     * @return {@link WriteCellData}<{@link ?}>
     * @throws Exception 异常
     */
    @Override
    public WriteCellData<?> convertToExcelData(Integer value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {

        String sexName;
        if(value == 0){
            sexName=MALE;
        }else if(value == 1){
            sexName=FEMALE;
        }else {
            sexName=UNKNOWN;
        }

        return new WriteCellData<>(sexName);
    }
}
