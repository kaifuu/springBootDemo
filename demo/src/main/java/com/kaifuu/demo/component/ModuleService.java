package com.kaifuu.demo.component;

import com.kaifuu.demo.model.BizCategory;
import com.kaifuu.demo.service.BizCategoryService;
import com.kaifuu.demo.service.SysConfigService;
import com.kaifuu.demo.util.CoreConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * js调用 thymeleaf 实现按钮权限
 */
@Component("module")
public class ModuleService {
    @Autowired
    private BizCategoryService bizCategoryService;

    @Autowired
    private SysConfigService sysConfigService;

    public Object get(String moduleName) {
        switch (moduleName) {
            case "categoryList"://分类
                BizCategory bizCategory = new BizCategory();
                bizCategory.setStatus(CoreConst.STATUS_VALID);
                List<BizCategory> list = bizCategoryService.selectCategories(bizCategory);
                return null;
            case "sysConfig":           //网站基本信息配置
                return sysConfigService.selectAll();
            default:
                return null;
        }
    }
}
