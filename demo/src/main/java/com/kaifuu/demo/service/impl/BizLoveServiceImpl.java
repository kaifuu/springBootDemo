package com.kaifuu.demo.service.impl;

import com.kaifuu.demo.mapper.BizLoveMapper;
import com.kaifuu.demo.model.BizLove;
import com.kaifuu.demo.service.BizLoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018-9-4.
 */
@Service
public class BizLoveServiceImpl extends BaseServiceImpl<BizLove> implements BizLoveService {
    @Autowired
    private BizLoveMapper loveMapper;
    @Override
    public BizLove checkLove(Integer bizId, String userIp) {
        return loveMapper.checkLove(bizId,userIp);
    }
}
