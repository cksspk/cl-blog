package cn.iocoder.yudao.module.blog.convert.blog;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;

/**
 * 博客 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface BlogConvert {

    BlogConvert INSTANCE = Mappers.getMapper(BlogConvert.class);

    BlogDO convert(BlogCreateReqVO bean);

    BlogDO convert(BlogUpdateReqVO bean);

    BlogRespVO convert(BlogDO bean);

    List<BlogRespVO> convertList(List<BlogDO> list);

    PageResult<BlogRespVO> convertPage(PageResult<BlogDO> page);

    List<BlogExcelVO> convertList02(List<BlogDO> list);

}
