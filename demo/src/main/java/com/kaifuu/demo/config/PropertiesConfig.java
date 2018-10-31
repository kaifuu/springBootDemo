package com.kaifuu.demo.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {

    private static Environment env;

    @Autowired
    public void setEnv(Environment env) {
        this.env = env;
    }

    public static String getString(String key) {
        return env.getProperty(key);
    }

    public static String getValue(String key) {
        String value = getString(key);
        if (!StringUtils.isEmpty(value)) {
            return StringUtils.trim(value);
        }
        return value;
    }

    public static String getValue(String key, String defaultValue) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return value;
    }

    public static int getIntValue(String key, int defaultValue) {
        String value = getValue(key);
        if (StringUtils.isEmpty(value)) {
            return defaultValue;
        }
        return NumberUtils.toInt(value);
    }
}
