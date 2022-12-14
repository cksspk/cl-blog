package cn.iocoder.yudao.module.blog.convert.tag;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.*;
import cn.iocoder.yudao.module.blog.controller.portal.tag.vo.PortalTagRespVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 博客标签 Convert
 *
 * @author 芋道源码
 */
@Mapper
public interface TagConvert {

    TagConvert INSTANCE = Mappers.getMapper(TagConvert.class);

    TagDO convert(TagCreateReqVO bean);

    TagDO convert(TagUpdateReqVO bean);

    TagRespVO convert(TagDO bean);

    List<TagRespVO> convertList(List<TagDO> list);

    PageResult<TagRespVO> convertPage(PageResult<TagDO> page);

    List<TagExcelVO> convertList02(List<TagDO> list);

    @Mapping(source = "title", target = "name")
    List<TagSimpleRespVO> convertList03(List<TagDO> list);


    List<PortalTagRespVO> convertPortalList(List<TagDO> list);

}
