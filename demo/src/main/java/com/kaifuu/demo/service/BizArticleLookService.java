package com.kaifuu.demo.service;

import com.kaifuu.demo.model.BizArticleLook;

import java.util.Date;

public interface BizArticleLookService extends BaseService<BizArticleLook> {
    int checkArticleLook(Integer articleId, String userIp, Date lookTime);
}
