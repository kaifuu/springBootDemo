package com.kaifuu.demo.mapper;

import com.kaifuu.demo.model.BizLove;
import com.kaifuu.demo.util.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface BizLoveMapper extends BaseMapper<BizLove> {
    BizLove checkLove(@Param("bizId") Integer bizId, @Param("userIp") String userIp);
}
