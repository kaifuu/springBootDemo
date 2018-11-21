package com.kaifuu.demo.service;

import com.kaifuu.demo.model.BizTheme;

public interface BizThemeService  extends BaseService<BizTheme>  {
    int useTheme(Integer id);

    BizTheme selectCurrent();

    int deleteBatch(Integer[] ids);

}
