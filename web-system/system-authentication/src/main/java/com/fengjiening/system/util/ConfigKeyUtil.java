package com.fengjiening.system.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @ClassName: ConfigKeyUtil
 * @Description: 获取配置文件参数
 * @Date: 2021/9/29 09:54
 * @Author: fengjiening::joko
 * @Version: 0.0
 */
@Component
public class ConfigKeyUtil {

    private final Environment environment;
    @Autowired
    private ConfigKeyUtil(Environment environment) {
        this.environment = environment;
    }


    private static Environment env;

    //PostConstruct注解不可以加参数
    @PostConstruct
    public void init(){
        env = this.environment;
    }

    //获取配置的值
    public static String getValue(String key){
        String value = env.getProperty(key);
        return value;
    }
}
