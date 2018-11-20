package com.kaifuu.demo.controller;

import com.google.code.kaptcha.Constants;
import com.kaifuu.demo.model.BizCategory;
import com.kaifuu.demo.model.Permission;
import com.kaifuu.demo.model.User;
import com.kaifuu.demo.service.BizCategoryService;
import com.kaifuu.demo.service.PermissionService;
import com.kaifuu.demo.service.SysConfigService;
import com.kaifuu.demo.service.UserService;
import com.kaifuu.demo.util.CoreConst;
import com.kaifuu.demo.util.PasswordHelper;
import com.kaifuu.demo.util.ResultUtil;
import com.kaifuu.demo.util.UUIDUtil;
import com.kaifuu.demo.vo.base.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class SystemController {
    @Autowired
    private UserService userService;
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

    //*提交注册*//*
    @PostMapping("/register")
    @ResponseBody
    public ResponseVo register(HttpServletRequest request, User registerUser, String confirmPassword, String verification) {
        //判断验证码
        String rightCode = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.isNotBlank(verification) && StringUtils.isNotBlank(rightCode) && verification.equals(rightCode)) {
            //验证码通过
        } else {
            return ResultUtil.error("验证码错误！");
        }
        String username = registerUser.getUsername();
        User user = userService.selectByUsername(username);
        if (null != user) {
            return ResultUtil.error("用户名已存在！");
        }
        String password = registerUser.getPassword();
        //判断两次输入密码是否相等
        if (confirmPassword != null && password != null) {
            if (!confirmPassword.equals(password)) {
                return ResultUtil.error("两次密码不一致！");
            }
        }
        registerUser.setUserId(UUIDUtil.getUniqueIdByUUId());
        registerUser.setStatus(CoreConst.STATUS_VALID);
        Date date = new Date();
        registerUser.setCreateTime(date);
        registerUser.setUpdateTime(date);
        registerUser.setLastLoginTime(date);
        PasswordHelper.encryptPassword(registerUser);
        //注册
        int registerResult = userService.register(registerUser);
        if (registerResult > 0) {
            return ResultUtil.success("注册成功！");
        } else {
            return ResultUtil.error("注册失败，请稍后再试！");
        }
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
