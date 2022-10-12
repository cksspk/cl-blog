package cn.iocoder.yudao.module.blog.convert.comment;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentRespVO;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentUpdateReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.comment.vo.PortalCommentCreateReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.vo.PortalCommentRespVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.comment.CommentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 评论 Convert
 *
 * @author cksspk
 */
@Mapper
public interface CommentConvert {

    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);



    CommentDO convert(CommentUpdateReqVO bean);

    CommentRespVO convert(CommentDO bean);



    PageResult<CommentRespVO> convertPage(PageResult<CommentDO> page);


    List<CommentRespVO> convertList(List<CommentDO> list);


    // ======= Portal
    CommentDO convert(PortalCommentCreateReqVO bean);


    PortalCommentRespVO convertPortal(CommentDO bean);

    default PageResult<PortalCommentRespVO> convertPortalPage(PageResult<CommentDO> page, HashMap<Long, PageResult<CommentDO>> subCommentMap) {

        List<PortalCommentRespVO> commentDOS = CollectionUtils.convertList(page.getList(), comment -> {
            PortalCommentRespVO respVO = convertPortal(comment);

            PageResult<CommentDO> subComment = subCommentMap.get(comment.getId());
            if (CollUtil.isNotEmpty(subComment.getList())) {
                respVO.setSubTotal(subComment.getTotal());
                List<CommentDO> list = subComment.getList();
                respVO.setSubCommentList(convertPortalList(list));
            }
            return respVO;
        });

        return new PageResult<>(commentDOS, page.getTotal());
    }

    /**
     * 三级评论翻译
     */
    default List<PortalCommentRespVO> convertPortalList(List<CommentDO>  list) {
        Map<Long, CommentDO> subCommentDOMap = CollectionUtils.convertMap(list, CommentDO::getId);

        return CollectionUtils.convertList(list, subComment -> {
            PortalCommentRespVO respVO = convertPortal(subComment);

            CommentDO replyComment = subCommentDOMap.get(subComment.getReplyId());
            if (ObjectUtil.isNotNull(replyComment)) {
                respVO.setReplyNickName(replyComment.getNickName());
            }

            return respVO;
        });


    }

}
