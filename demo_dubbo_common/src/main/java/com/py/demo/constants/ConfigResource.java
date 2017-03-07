package com.py.demo.constants;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by zczhao on 16/3/7.
 */

@Configuration
public class ConfigResource {

    @Value("${com.ibbpp.mipush.debug}")//配置文件中的key
    private String mipushDebug;

    @Value("${com.ibbpp.dev.debug}")
    private String devMode;


    @PostConstruct
    public void init() {//初始化
        if (StringUtils.isNotBlank(mipushDebug) && mipushDebug.equals("true")) {
            //// TODO: 2016/12/21  
        } else {
            //// TODO: 2016/12/21
        }

        Constant.DEV_MODE = devMode; //用常量中的值覆盖配置文件中的值
    }

}
