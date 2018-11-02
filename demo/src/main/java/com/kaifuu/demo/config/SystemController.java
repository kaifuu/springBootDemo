package com.kaifuu.demo.config;

import com.kaifuu.demo.model.BizCategory;
import com.kaifuu.demo.service.BizCategoryService;
import com.kaifuu.demo.util.CoreConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SystemController {
    @Autowired
    private BizCategoryService bizCategoryService;

    /*后台首页*/
    @RequestMapping(value={"/index"})
    public String index(){
        return "index/index";
    }

   //*注册*//*
    @GetMapping(value = "/register")
    public String register(Model model){
        BizCategory bizCategory = new BizCategory();
        bizCategory.setStatus(CoreConst.STATUS_VALID);
        model.addAttribute("typeList",bizCategoryService.selectCategories(bizCategory));
        return "system/register";
    }


}
