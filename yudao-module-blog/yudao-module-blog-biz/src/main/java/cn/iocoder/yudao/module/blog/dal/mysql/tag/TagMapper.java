package cn.iocoder.yudao.module.blog.dal.mysql.tag;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.*;

/**
 * 博客标签 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TagMapper extends BaseMapperX<TagDO> {

    default PageResult<TagDO> selectPage(TagPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TagDO>()
                .eqIfPresent(TagDO::getTitle, reqVO.getTitle())
                .betweenIfPresent(TagDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TagDO::getId));
    }

    default List<TagDO> selectList(TagExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<TagDO>()
                .eqIfPresent(TagDO::getTitle, reqVO.getTitle())
                .betweenIfPresent(TagDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TagDO::getId));
    }

    default List<TagDO> selectList(String title) {
        return selectList(new LambdaQueryWrapperX<TagDO>()
                .likeIfPresent(TagDO::getTitle, title)
                .orderByDesc(TagDO::getId));
    }

    default TagDO selectByTitle(String title){
        return selectOne(TagDO::getTitle, title);
    }

}
