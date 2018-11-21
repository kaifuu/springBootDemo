package com.kaifuu.demo.service.impl;

import com.kaifuu.demo.mapper.BizTagsMapper;
import com.kaifuu.demo.model.BizTags;
import com.kaifuu.demo.service.BizTagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizTagsServiceImpl extends BaseServiceImpl<BizTags> implements BizTagsService {

    @Autowired
    private BizTagsMapper bizTagsMapper;

    @Override
    public List<BizTags> selectTags(BizTags bizTags) {
        return bizTagsMapper.selectTags(bizTags);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return bizTagsMapper.deleteBatch(ids);
    }
}
