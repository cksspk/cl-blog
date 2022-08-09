package cn.iocoder.yudao.module.blog.convert.category;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.blog.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.category.BlogCategoryDO;

/**
 * 分类 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface BlogCategoryConvert {

    BlogCategoryConvert INSTANCE = Mappers.getMapper(BlogCategoryConvert.class);

    BlogCategoryDO convert(BlogCategoryCreateReqVO bean);

    BlogCategoryDO convert(BlogCategoryUpdateReqVO bean);

    BlogCategoryRespVO convert(BlogCategoryDO bean);

    List<BlogCategoryRespVO> convertList(List<BlogCategoryDO> list);

    PageResult<BlogCategoryRespVO> convertPage(PageResult<BlogCategoryDO> page);

    List<BlogCategoryExcelVO> convertList02(List<BlogCategoryDO> list);

    @Mapping(source = "title", target = "name")
    List<CategorySimpleRespVO> convertList03(List<BlogCategoryDO> list);
}
