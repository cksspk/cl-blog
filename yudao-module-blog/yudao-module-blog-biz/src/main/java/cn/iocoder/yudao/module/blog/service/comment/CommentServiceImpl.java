package cn.iocoder.yudao.module.blog.service.comment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentCreateReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentPageReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentUpdateReqVO;
import cn.iocoder.yudao.module.blog.convert.comment.CommentConvert;
import cn.iocoder.yudao.module.blog.dal.dataobject.comment.CommentDO;
import cn.iocoder.yudao.module.blog.dal.mysql.comment.CommentMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blog.enums.ErrorCodeConstants.COMMENT_NOT_EXISTS;


/**
 * 评论 Service 实现类
 *
 * @author cksspk
 */
@Service
@Validated
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public Long createComment(CommentCreateReqVO createReqVO) {
        // 插入
        CommentDO comment = CommentConvert.INSTANCE.convert(createReqVO);
        commentMapper.insert(comment);
        // 返回
        return comment.getId();
    }

    @Override
    public void updateComment(CommentUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCommentExists(updateReqVO.getId());
        // 更新
        CommentDO updateObj = CommentConvert.INSTANCE.convert(updateReqVO);
        commentMapper.updateById(updateObj);
    }

    @Override
    public void deleteComment(Long id) {
        // 校验存在
        this.validateCommentExists(id);
        // 删除
        commentMapper.deleteById(id);
    }

    private void validateCommentExists(Long id) {
        if (commentMapper.selectById(id) == null) {
            throw exception(COMMENT_NOT_EXISTS);
        }
    }

    @Override
    public CommentDO getComment(Long id) {
        return commentMapper.selectById(id);
    }

    @Override
    public List<CommentDO> getCommentList(Collection<Long> ids) {
        return commentMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<CommentDO> getCommentPage(CommentPageReqVO pageReqVO) {
        return commentMapper.selectPage(pageReqVO);
    }



}
