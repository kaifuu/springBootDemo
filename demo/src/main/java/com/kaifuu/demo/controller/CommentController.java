package com.kaifuu.demo.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.kaifuu.demo.model.BizComment;
import com.kaifuu.demo.model.User;
import com.kaifuu.demo.service.BizCommentService;
import com.kaifuu.demo.util.CoreConst;
import com.kaifuu.demo.util.IpUtil;
import com.kaifuu.demo.util.PageUtil;
import com.kaifuu.demo.util.ResultUtil;
import com.kaifuu.demo.vo.CommentConditionVo;
import com.kaifuu.demo.vo.base.PageResultVo;
import com.kaifuu.demo.vo.base.ResponseVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private BizCommentService commentService;

    @PostMapping("list")
    public PageResultVo loadNotify(CommentConditionVo comment, Integer limit, Integer offset){
        PageHelper.startPage(PageUtil.getPageNo(limit, offset),limit);
        List<BizComment> comments = commentService.selectComments(comment);
        PageInfo<BizComment> pages = new PageInfo<>(comments);
        return ResultUtil.table(comments,pages.getTotal());
    }
    @PostMapping("/reply")
    public ResponseVo edit(BizComment comment){
        completeComment(comment);
        int i = commentService.insertSelective(comment);
        if(i>0){
            return ResultUtil.success("回复评论成功");
        }else{
            return ResultUtil.error("回复评论失败");
        }
    }
    @PostMapping("/delete")
    public ResponseVo delete(Integer id){
        Integer[]ids={id};
        int i = commentService.deleteBatch(ids);
        if(i>0){
            return ResultUtil.success("删除评论成功");
        }else{
            return ResultUtil.error("删除评论失败");
        }
    }
    @PostMapping("/batch/delete")
    public ResponseVo deleteBatch(@RequestParam("ids[]") Integer[]ids){
        int i = commentService.deleteBatch(ids);
        if(i>0){
            return ResultUtil.success("删除评论成功");
        }else{
            return ResultUtil.error("删除评论失败");
        }
    }

    @PostMapping("/audit")
    public ResponseVo audit(BizComment bizComment, String replyContent){
        try {
            commentService.updateNotNull(bizComment);
            if(StringUtils.isNotBlank(replyContent)){
                BizComment replyComment = new BizComment();
                replyComment.setPid(bizComment.getId());
                replyComment.setSid(bizComment.getSid());
                replyComment.setContent(replyContent);
                completeComment(replyComment);
                commentService.insertSelective(replyComment);
            }
            return ResultUtil.success("审核成功");
        } catch (Exception e) {
            return ResultUtil.success("审核失败");
        }
    }

    private void completeComment(BizComment comment){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        comment.setUserId(user.getUserId());
        comment.setNickname(user.getNickname());
        comment.setEmail(user.getEmail());
        comment.setAvatar(user.getImg());
        comment.setIp(IpUtil.getIpAddr(request));
        comment.setStatus(CoreConst.STATUS_VALID);
    }

}
