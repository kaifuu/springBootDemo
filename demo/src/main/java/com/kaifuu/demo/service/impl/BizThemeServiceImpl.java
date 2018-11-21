package com.kaifuu.demo.service.impl;

import com.kaifuu.demo.config.redis.RedisCache;
import com.kaifuu.demo.mapper.BizThemeMapper;
import com.kaifuu.demo.model.BizTheme;
import com.kaifuu.demo.service.BizThemeService;
import com.kaifuu.demo.util.CoreConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BizThemeServiceImpl extends BaseServiceImpl<BizTheme> implements BizThemeService {

    @Autowired
    private BizThemeMapper themeMapper;
    @Override
    @RedisCache(flush = true)
    public int useTheme(Integer id) {
        themeMapper.setInvaid();
        return themeMapper.updateStatusById(id);
    }

    @Override
    @RedisCache
    public BizTheme selectCurrent() {
        BizTheme bizTheme = new BizTheme();
        bizTheme.setStatus(CoreConst.STATUS_VALID);
        return themeMapper.selectOne(bizTheme);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return themeMapper.deleteBatch(ids);
    }
}
