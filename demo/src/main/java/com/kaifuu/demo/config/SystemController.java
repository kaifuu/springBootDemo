package com.kaifuu.demo.config;

import com.kaifuu.demo.model.BizCategory;
import com.kaifuu.demo.model.Permission;
import com.kaifuu.demo.model.User;
import com.kaifuu.demo.service.BizCategoryService;
import com.kaifuu.demo.service.PermissionService;
import com.kaifuu.demo.service.SysConfigService;
import com.kaifuu.demo.util.CoreConst;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class SystemController {
    @Autowired
    private BizCategoryService bizCategoryService;

    @Autowired
    private PermissionService permissionService;
    @Autowired
    private SysConfigService configService;

    /*后台首页*/
    @RequestMapping(value = {"/index"})
    public String index() {
        return "index/index";
    }

    //*注册*//*
    @GetMapping(value = "/register")
    public String register(Model model) {
        BizCategory bizCategory = new BizCategory();
        bizCategory.setStatus(CoreConst.STATUS_VALID);
        model.addAttribute("typeList", bizCategoryService.selectCategories(bizCategory));
        return "system/register";
    }

    /*获取当前登录用户的菜单*/
    @PostMapping("/menu")
    @ResponseBody
    public List<Permission> getMenus() {
        List<Permission> permissionListList = permissionService.selectMenuByUserId(((User) SecurityUtils.getSubject().getPrincipal()).getUserId());
        return permissionListList;
    }

    /*登陆*/
    @GetMapping("/login")
    public String login(Model model) {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        BizCategory bizCategory = new BizCategory();
        bizCategory.setStatus(CoreConst.STATUS_VALID);
        model.addAttribute("categoryList", bizCategoryService.selectCategories(bizCategory));
        getSysConfig(model);
        return "system/login";
    }

    private void getSysConfig(Model model) {
        Map<String, String> map = configService.selectAll();
        model.addAttribute("sysConfig", map);
        BizCategory bizCategory = new BizCategory();
        bizCategory.setStatus(CoreConst.STATUS_VALID);
        model.addAttribute("categoryList", bizCategoryService.selectCategories(bizCategory));
    }

}
