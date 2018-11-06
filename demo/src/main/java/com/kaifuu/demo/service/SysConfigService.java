package com.kaifuu.demo.service;

import java.util.Map;

/**
 * Created by Administrator on 2018/3/1.
 */
public interface SysConfigService {

    Map<String,String> selectAll();

    int updateByKey(String key, String value);
}
