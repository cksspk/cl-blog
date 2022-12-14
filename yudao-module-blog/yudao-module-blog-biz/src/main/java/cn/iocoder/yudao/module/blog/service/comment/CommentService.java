package cn.iocoder.yudao.module.blog.service.comment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentPageReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentUpdateReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.comment.vo.PortalCommentCreateReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.vo.PortalCommentPageReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.vo.PortalCommentRespVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.comment.CommentDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 评论 Service 接口
 *
 * @author cksspk
 */
public interface CommentService {

    /**
     * 创建评论
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createComment(@Valid PortalCommentCreateReqVO createReqVO);

    /**
     * 更新评论
     *
     * @param updateReqVO 更新信息
     */
    void updateComment(@Valid CommentUpdateReqVO updateReqVO);

    /**
     * 删除评论
     *
     * @param id 编号
     */
    void deleteComment(Long id);

    /**
     * 获得评论
     *
     * @param id 编号
     * @return 评论
     */
    CommentDO getComment(Long id);

    /**
     * 获得评论列表
     *
     * @param ids 编号
     * @return 评论列表
     */
    List<CommentDO> getCommentList(Collection<Long> ids);

    /**
     * 获得评论分页
     *
     * @param pageReqVO 分页查询
     * @return 评论分页
     */
    PageResult<CommentDO> getCommentPage(CommentPageReqVO pageReqVO);


    /**
     * 获取博客评论的数量
     * @param blogId
     * @return
     */
    Long getCommentCountByBlogId(Long blogId);


    /**
     * 根据博客编号查询评论列表
     * @param reqVO 查询条件
     * @return 评论分页
     */
    PageResult<PortalCommentRespVO> getCommentPageByBlogId(PortalCommentPageReqVO reqVO);
}
