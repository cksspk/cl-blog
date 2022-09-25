package cn.iocoder.yudao.module.blog.convert.comment;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentCreateReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentRespVO;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentUpdateReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.vo.PortalCommentRespVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.comment.CommentDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 评论 Convert
 *
 * @author cksspk
 */
@Mapper
public interface CommentConvert {

    CommentConvert INSTANCE = Mappers.getMapper(CommentConvert.class);

    CommentDO convert(CommentCreateReqVO bean);

    CommentDO convert(CommentUpdateReqVO bean);

    CommentRespVO convert(CommentDO bean);



    PageResult<CommentRespVO> convertPage(PageResult<CommentDO> page);


    List<CommentRespVO> convertList(List<CommentDO> list);


    // ======= Portal
    PageResult<PortalCommentRespVO> convertPortalPage(PageResult<CommentDO> list);


}
