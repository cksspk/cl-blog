package cn.iocoder.yudao.module.blog.dal.mysql.comment;


import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentPageReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.vo.PortalCommentPageReqVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.comment.CommentDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 评论 Mapper
 *
 * @author cksspk
 */
@Mapper
public interface CommentMapper extends BaseMapperX<CommentDO> {

    default PageResult<CommentDO> selectPage(CommentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommentDO>()
                .likeIfPresent(CommentDO::getNickName, reqVO.getNickName())
                .eqIfPresent(CommentDO::getEmail, reqVO.getEmail())
                .eqIfPresent(CommentDO::getIp, reqVO.getIp())
                .eqIfPresent(CommentDO::getLocation, reqVO.getLocation())
                .eqIfPresent(CommentDO::getOs, reqVO.getOs())
                .eqIfPresent(CommentDO::getBrowser, reqVO.getBrowser())
                .eqIfPresent(CommentDO::getParentId, reqVO.getParentId())
                .eqIfPresent(CommentDO::getQqNum, reqVO.getQqNum())
                .eqIfPresent(CommentDO::getAvatar, reqVO.getAvatar())
                .eqIfPresent(CommentDO::getBlogId, reqVO.getBlogId())
                .eqIfPresent(CommentDO::getUrl, reqVO.getUrl())
                .eqIfPresent(CommentDO::getDisplay, reqVO.getDisplay())
                .eqIfPresent(CommentDO::getGood, reqVO.getGood())
                .eqIfPresent(CommentDO::getBad, reqVO.getBad())
                .eqIfPresent(CommentDO::getContent, reqVO.getContent())
                .eqIfPresent(CommentDO::getHtmlContent, reqVO.getHtmlContent())
                .eqIfPresent(CommentDO::getReply, reqVO.getReply())
                .eqIfPresent(CommentDO::getAdminReply, reqVO.getAdminReply())
                .eqIfPresent(CommentDO::getReplyId, reqVO.getReplyId())
                .betweenIfPresent(CommentDO::getCreateTime, reqVO.getBeginCreateTime(), reqVO.getEndCreateTime())
                .orderByDesc(CommentDO::getId));
    }



    default List<CommentDO> selectListByBlogId(Long blogId) {
        return selectList(CommentDO::getBlogId, blogId);
    }

    default Long selectCountByBlogId(Long blogId) {
        return selectCount(CommentDO::getBlogId, blogId);
    }

    // ====== Portal


    default PageResult<CommentDO> selectPortalCommentRootPage(PortalCommentPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CommentDO>()
                .eq(CommentDO::getBlogId, reqVO.getBlogId())
                .isNull(CommentDO::getParentId)
                // 按点赞排序 TODO 后期优化排序
                .orderByDesc(CommentDO::getGood));
    }


    /**
     * 子评论查询，按点赞逆序
     * @param pageParam 只查询两条
     * @param blogId 博客编号
     * @param rootId 一级评论编号
     * @return 评论分页
     */
    default PageResult<CommentDO> selectChildComments(PageParam pageParam, Long blogId, Long rootId) {
        return selectPage(pageParam, new LambdaQueryWrapperX<CommentDO>()
                .eq(CommentDO::getBlogId, blogId)
                .eq(CommentDO::getRootId, rootId)
                .isNotNull(CommentDO::getParentId)
                // 按点赞排序
                .orderByDesc(CommentDO::getGood));
    }
}
