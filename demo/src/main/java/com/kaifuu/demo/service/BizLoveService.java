package com.kaifuu.demo.service;

import com.kaifuu.demo.model.BizLove;

public interface BizLoveService extends BaseService<BizLove> {
    BizLove checkLove(Integer bizId, String userIp);
}
