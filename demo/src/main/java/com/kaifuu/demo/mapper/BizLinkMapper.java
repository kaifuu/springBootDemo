package com.kaifuu.demo.mapper;


import com.kaifuu.demo.model.BizLink;
import com.kaifuu.demo.util.BaseMapper;

import java.util.List;

public interface BizLinkMapper extends BaseMapper<BizLink> {

    List<BizLink> selectLinks(BizLink bizLink);

    int deleteBatch(Integer[] ids);

}
