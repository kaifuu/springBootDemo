package com.kaifuu.demo.mapper;

import com.kaifuu.demo.model.BizArticleLook;
import com.kaifuu.demo.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface BizArticleLookMapper extends BaseMapper<BizArticleLook> {

    int checkArticleLook(@Param("articleId") Integer articleId, @Param("userIp") String userIp, @Param("lookTime") Date lookTime);
}
