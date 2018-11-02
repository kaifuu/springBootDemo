package com.kaifuu.demo.mapper;

import com.kaifuu.demo.model.BizCategory;
import com.kaifuu.demo.util.BaseMapper;

import java.util.List;

public interface BizCategoryMapper extends BaseMapper<BizCategory> {

    List<BizCategory> selectCategories(BizCategory bizCategory);

    int deleteBatch(Integer[] ids);

    BizCategory selectById(Integer id);
}
