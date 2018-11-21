package com.kaifuu.demo.model;


import com.kaifuu.demo.vo.base.BaseVo;

/**
 * @version V1.0
 * @date 2018年7月11日
 * @author superzheng
 */
public class BizArticleTags extends BaseVo {
    private Integer tagId;
    private Integer articleId;

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
