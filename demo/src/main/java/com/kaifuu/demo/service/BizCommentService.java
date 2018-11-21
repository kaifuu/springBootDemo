package com.kaifuu.demo.service;


import com.kaifuu.demo.model.BizComment;
import com.kaifuu.demo.vo.CommentConditionVo;

import java.util.List;

public interface BizCommentService extends BaseService<BizComment> {
    List<BizComment> selectComments(CommentConditionVo vo);

    int deleteBatch(Integer[] ids);

}
