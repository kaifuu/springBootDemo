package com.kaifuu.demo.model;


import com.kaifuu.demo.vo.base.BaseVo;

public class BizTags extends BaseVo {
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
