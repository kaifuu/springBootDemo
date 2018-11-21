package com.kaifuu.demo.service.impl;

import com.kaifuu.demo.mapper.BizCommentMapper;
import com.kaifuu.demo.model.BizComment;
import com.kaifuu.demo.service.BizCommentService;
import com.kaifuu.demo.vo.CommentConditionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BizCommentServiceImpl extends BaseServiceImpl<BizComment> implements BizCommentService {
    @Autowired
    private BizCommentMapper commentMapper;
    @Override
    public List<BizComment> selectComments(CommentConditionVo vo) {
        return commentMapper.selectComments(vo);
    }

    @Override
    public int deleteBatch(Integer[] ids) {
        return commentMapper.deleteBatch(ids);
    }
}
