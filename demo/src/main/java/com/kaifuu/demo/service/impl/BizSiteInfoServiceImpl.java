package com.kaifuu.demo.service.impl;

import com.kaifuu.demo.mapper.BizArticleMapper;
import com.kaifuu.demo.service.BizSiteInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class BizSiteInfoServiceImpl implements BizSiteInfoService {
    @Autowired
    private BizArticleMapper bizArticleMapper;

    @Override
    public Map<String, Object> getSiteInfo() {
        Map<String, Object> map = bizArticleMapper.getSiteInfo();
        return map;
    }

}
