package com.coding.guide.common.config;

import com.coding.guide.common.utils.BrowserUtils;
import com.coding.guide.common.utils.EasyExcelUtil;
import com.coding.guide.common.utils.IpToAddressUtil;
import com.coding.guide.common.utils.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 工具类（utils）bean配置
 *
 * @author youzhengjie
 * @date 2022/11/18 00:18:29
 */
@Configuration
@Import({
        BrowserUtils.class,
        EasyExcelUtil.class,
        IpToAddressUtil.class,
        JwtUtil.class
})
public class UtilsConfiguration {



}
