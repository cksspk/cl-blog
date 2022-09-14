package cn.iocoder.yudao.module.blog.dal.mysql.tag;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagExportReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagPageReqVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagDO;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagMappingDO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 博客标签 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TagMappingMapper extends BaseMapperX<TagMappingDO> {


    default void deleteTagMappingByBlogId(Long blogId) {
        delete(new LambdaQueryWrapperX<TagMappingDO>()
                .eq(TagMappingDO::getBlogId, blogId)
                );
    }

    default List<TagMappingDO> selectTagMappingByBlogId(Long blogId) {
        return selectList(new LambdaQueryWrapperX<TagMappingDO>()
                .eq(TagMappingDO::getBlogId, blogId)
        );
    }
}
