package cn.iocoder.yudao.module.blog.convert.blog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 博客 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface BlogConvert {

    BlogConvert INSTANCE = Mappers.getMapper(BlogConvert.class);

    // 发布
    BlogDO convert(BlogPublishCreateReqVO bean);
    BlogDO convert(BlogPublishUpdateReqVO bean);

    // 草稿
    BlogDO convert(BlogDraftCreateReqVO bean);
    BlogDO convert(BlogDraftUpdateReqVO bean);

    BlogRespVO convert(BlogDO bean);

    List<BlogRespVO> convertList(List<BlogDO> list);

    PageResult<BlogRespVO> convertPage(PageResult<BlogDO> page);


}
