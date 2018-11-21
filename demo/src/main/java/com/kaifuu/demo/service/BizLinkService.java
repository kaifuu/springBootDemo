package com.kaifuu.demo.service;

import com.kaifuu.demo.model.BizLink;

import java.util.List;

public interface BizLinkService extends BaseService<BizLink> {
    List<BizLink> selectLinks(BizLink bizLink);

    int deleteBatch(Integer[] ids);

}
