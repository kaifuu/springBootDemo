package com.kaifuu.demo.mapper;


import com.kaifuu.demo.model.BizTags;
import com.kaifuu.demo.util.BaseMapper;

import java.util.List;

public interface BizTagsMapper extends BaseMapper<BizTags> {

    List<BizTags> selectTags(BizTags bizTags);

    int deleteBatch(Integer[] ids);
}
