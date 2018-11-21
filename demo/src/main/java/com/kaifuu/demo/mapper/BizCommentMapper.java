package com.kaifuu.demo.mapper;

import com.kaifuu.demo.model.BizComment;
import com.kaifuu.demo.util.BaseMapper;
import com.kaifuu.demo.vo.CommentConditionVo;

import java.util.List;

public interface BizCommentMapper extends BaseMapper<BizComment> {

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    List<BizComment> selectComments(CommentConditionVo vo);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    int deleteBatch(Integer[] ids);
}
