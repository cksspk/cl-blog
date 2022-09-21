package cn.iocoder.yudao.module.blog.convert.blog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.*;
import cn.iocoder.yudao.module.blog.controller.portal.blog.vo.PortalBlogPageRespVO;
import cn.iocoder.yudao.module.blog.controller.portal.blog.vo.PortalBlogRespVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import cn.iocoder.yudao.module.blog.dal.dataobject.category.BlogCategoryDO;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

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


    List<BlogPageRespVO> convertList02(List<BlogDO> list);

    BlogPageRespVO.Category convert(BlogCategoryDO bean);

    default PageResult<BlogPageRespVO> convertPage(PageResult<BlogDO> page, Map<Long, BlogCategoryDO> categoryMap) {
        List<BlogPageRespVO> list = convertList02(page.getList());
        list.forEach(respVO -> respVO.setCategory(convert(categoryMap.get(respVO.getCategoryId()))));
        return new PageResult<>(list, page.getTotal());
    }


    default BlogRespVO convert(BlogDO blogDO, List<TagDO> tagListByBlogId) {
        BlogRespVO respVO = convert(blogDO);
        respVO.setTagTitleList(CollectionUtils.convertList(tagListByBlogId, TagDO::getTitle));
        return respVO;
    }


    //====== portal
    List<PortalBlogRespVO> convertPortalList(List<BlogDO> list);

    PageResult<PortalBlogPageRespVO> convertPortalPage(PageResult<BlogDO> pageResult);

    PortalBlogRespVO convertPortal(BlogDO blogDO, Long commentCount);
}
