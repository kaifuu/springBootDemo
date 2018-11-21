package com.kaifuu.demo.service.impl;

import com.kaifuu.demo.mapper.BizLinkMapper;
import com.kaifuu.demo.model.BizLink;
import com.kaifuu.demo.service.BizLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizLinkServiceImpl extends BaseServiceImpl<BizLink> implements BizLinkService {
    @Autowired
    private BizLinkMapper linkMapper;
    @Override
    public List<BizLink>  selectLinks(BizLink bizLink) {
        return linkMapper.selectLinks(bizLink);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return linkMapper.deleteBatch(ids);
    }

}
