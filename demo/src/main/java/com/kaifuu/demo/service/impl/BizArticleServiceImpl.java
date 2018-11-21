package com.kaifuu.demo.service.impl;

import com.github.pagehelper.PageHelper;
import com.kaifuu.demo.mapper.BizArticleMapper;
import com.kaifuu.demo.model.BizArticle;
import com.kaifuu.demo.service.BizArticleService;
import com.kaifuu.demo.util.CoreConst;
import com.kaifuu.demo.vo.ArticleConditionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class BizArticleServiceImpl extends BaseServiceImpl<BizArticle> implements BizArticleService {
    @Autowired
    private BizArticleMapper bizArticleMapper;


    @Override
    public List<BizArticle> findByCondition(ArticleConditionVo vo) {
        List<BizArticle> list = bizArticleMapper.findByCondition(vo);
        if (!CollectionUtils.isEmpty(list)) {
            List<Integer> ids = new ArrayList<>();
            for (BizArticle bizArticle : list) {
                ids.add(bizArticle.getId());
            }
            List<BizArticle> listTag = bizArticleMapper.listTagsByArticleId(ids);
            // listTag, 重新组装数据为{id: Article}
            Map<Integer, BizArticle> tagMap = new LinkedHashMap<>(listTag.size());
            for (BizArticle bizArticle : listTag) {
                tagMap.put(bizArticle.getId(), bizArticle);
            }

            for (BizArticle bizArticle : list) {
                BizArticle tagArticle = tagMap.get(bizArticle.getId());
                bizArticle.setTags(tagArticle.getTags());
            }
        }
        return list;
    }

    @Override
    public List<BizArticle> sliderList() {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setSlider(true);
        vo.setStatus(CoreConst.STATUS_VALID);
        return this.findByCondition(vo);
    }

    @Override
    public List<BizArticle> recommendedList(int pageSize) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setRecommended(true);
        vo.setStatus(CoreConst.STATUS_VALID);
        vo.setPageSize(pageSize);
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        return this.findByCondition(vo);
    }

    @Override
    public List<BizArticle> recentList(int pageSize) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setPageSize(pageSize);
        vo.setStatus(CoreConst.STATUS_VALID);
        vo.setRecentFlag(true);
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        return this.findByCondition(vo);
    }

    @Override
    public List<BizArticle> randomList(int pageSize) {
        ArticleConditionVo vo = new ArticleConditionVo();
        vo.setRandom(true);
        vo.setStatus(CoreConst.STATUS_VALID);
        vo.setPageSize(pageSize);
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        return this.findByCondition(vo);
    }

    @Override
    public List<BizArticle> hotList(int pageSize) {
        PageHelper.startPage(1, pageSize);
        return bizArticleMapper.hotList();
    }

    @Override
    public BizArticle selectById(Integer id) {
        return bizArticleMapper.selectById(id);
    }

    @Override
    public BizArticle insertArticle(BizArticle bizArticle) {
        Date date = new Date();
        bizArticle.setCreateTime(date);
        bizArticle.setUpdateTime(date);
        bizArticleMapper.insertSelective(bizArticle);
        return bizArticle;
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return bizArticleMapper.deleteBatch(ids);
    }

    @Override
    public List<BizArticle> selectByCategoryId(Integer categoryId) {
        BizArticle article = new BizArticle();
        article.setCategoryId(categoryId);
        return bizArticleMapper.select(article);
    }
}
