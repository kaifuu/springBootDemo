package com.kaifuu.demo.mapper;

import com.kaifuu.demo.model.BizTheme;
import com.kaifuu.demo.util.BaseMapper;

public interface BizThemeMapper extends BaseMapper<BizTheme> {
    int setInvaid();
    int updateStatusById(Integer id);
    int deleteBatch(Integer[] ids);
}