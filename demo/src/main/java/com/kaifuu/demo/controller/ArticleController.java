package com.kaifuu.demo.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaifuu.demo.model.BizArticle;
import com.kaifuu.demo.model.BizCategory;
import com.kaifuu.demo.model.BizTags;
import com.kaifuu.demo.model.User;
import com.kaifuu.demo.service.BizArticleService;
import com.kaifuu.demo.service.BizArticleTagsService;
import com.kaifuu.demo.service.BizCategoryService;
import com.kaifuu.demo.service.BizTagsService;
import com.kaifuu.demo.util.CoreConst;
import com.kaifuu.demo.util.PageUtil;
import com.kaifuu.demo.util.ResultUtil;
import com.kaifuu.demo.vo.ArticleConditionVo;
import com.kaifuu.demo.vo.base.PageResultVo;
import com.kaifuu.demo.vo.base.ResponseVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private BizArticleService articleService;
    @Autowired
    private BizArticleTagsService articleTagsService;
    @Autowired
    private BizCategoryService categoryService;
    @Autowired
    private BizTagsService tagsService;

    @PostMapping("list")
    @ResponseBody
    public PageResultVo loadArticle(ArticleConditionVo articleConditionVo, Integer limit, Integer offset){
        articleConditionVo.setSliderFlag(true);
        PageHelper.startPage(PageUtil.getPageNo(limit, offset),limit);
        List<BizArticle> articleList= articleService.findByCondition(articleConditionVo);
        PageInfo<BizArticle> pages = new PageInfo<>(articleList);
        return ResultUtil.table(articleList,pages.getTotal());
    }
    /*文章*/
    @GetMapping("/add")
    public String addArticle(Model model){
        BizCategory bizCategory = new BizCategory();
        bizCategory.setStatus(CoreConst.STATUS_VALID);
        List<BizCategory> bizCategories = categoryService.selectCategories(bizCategory);
        List<BizTags> tags = tagsService.select(new BizTags());
        model.addAttribute("categories", JSON.toJSONString(bizCategories));
        model.addAttribute("tags",tags);
        return "article/publish";
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(BizArticle bizArticle, Integer[]tag){
        try{
            User user = (User)SecurityUtils.getSubject().getPrincipal();
            bizArticle.setUserId(user.getUserId());
            bizArticle.setAuthor(user.getNickname());
            BizArticle article = articleService.insertArticle(bizArticle);
            articleTagsService.insertList(tag, article.getId());
            return ResultUtil.success("保存文章成功");
        }catch (Exception e){
            return ResultUtil.error("保存文章失败");
        }
    }

    @GetMapping("/edit")
    public String edit(Model model, Integer id){
        BizArticle bizArticle= articleService.selectById(id);
        model.addAttribute("article",bizArticle);
        BizCategory bizCategory = new BizCategory();
        bizCategory.setStatus(CoreConst.STATUS_VALID);
        List<BizCategory> bizCategories = categoryService.selectCategories(bizCategory);
        model.addAttribute("categories", JSON.toJSONString(bizCategories));
        List<BizTags> sysTags = tagsService.select(new BizTags());
        /*方便前端处理回显*/
        List<BizTags> aTags = new ArrayList<>();
        List<BizTags> sTags = new ArrayList<>();
        boolean flag;
        for(BizTags sysTag:sysTags){
            flag=false;
            for(BizTags articleTag: bizArticle.getTags()){
                if(articleTag.getId().equals(sysTag.getId())){
                    BizTags tempTags = new BizTags();
                    tempTags.setId(sysTag.getId());
                    tempTags.setName(sysTag.getName());
                    aTags.add(tempTags);
                    sTags.add(tempTags);
                    flag=true;
                    break;
                }
            }
            if(!flag){
                sTags.add(sysTag);
            }
        }
        bizArticle.setTags(aTags);
        model.addAttribute("tags",sTags);
        return "article/detail";
    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(BizArticle article,Integer[]tag){
        articleService.updateNotNull(article);
        articleTagsService.removeByArticleId(article.getId());
        articleTagsService.insertList(tag, article.getId());
        return ResultUtil.success("编辑文章成功");
    }
    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo delete(Integer id){
        int i = articleService.deleteBatch(new Integer[]{id});
        if(i>0){
            return ResultUtil.success("删除文章成功");
        }else{
            return ResultUtil.error("删除文章失败");
        }
    }
    @PostMapping("/batch/delete")
    @ResponseBody
    public ResponseVo deleteBatch(@RequestParam("ids[]") Integer[]ids){
        int i = articleService.deleteBatch(ids);
        if(i>0){
            return ResultUtil.success("删除文章成功");
        }else{
            return ResultUtil.error("删除文章失败");
        }
    }
}
