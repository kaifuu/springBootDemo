package com.kaifuu.demo.service.impl;

import com.kaifuu.demo.mapper.BizCategoryMapper;
import com.kaifuu.demo.model.BizCategory;
import com.kaifuu.demo.service.BizCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizCategoryServiceImpl extends BaseServiceImpl<BizCategory> implements BizCategoryService {
    @Autowired
    private BizCategoryMapper bizCategoryMapper;
    @Override
    public List<BizCategory> selectCategories(BizCategory bizCategory) {
        return bizCategoryMapper.selectCategories(bizCategory);
    }
    @Override
    public int deleteBatch(Integer[] ids) {
        return bizCategoryMapper.deleteBatch(ids);
    }

    @Override
    public BizCategory selectById(Integer id) {
        return bizCategoryMapper.selectById(id);
    }

    @Override
    public List<BizCategory> selectByPid(Integer pid) {
        BizCategory bizCategory = new BizCategory();
        bizCategory.setPid(pid);
        return bizCategoryMapper.select(bizCategory);
    }
}
