package cn.iocoder.yudao.module.blog.dal.mysql.category;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.blog.dal.dataobject.category.BlogCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.blog.controller.admin.category.vo.*;

/**
 * 分类 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BlogCategoryMapper extends BaseMapperX<BlogCategoryDO> {

    default PageResult<BlogCategoryDO> selectPage(BlogCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BlogCategoryDO>()
                .eqIfPresent(BlogCategoryDO::getTitle, reqVO.getTitle())
                .eqIfPresent(BlogCategoryDO::getDescription, reqVO.getDescription())
                .eqIfPresent(BlogCategoryDO::getSupport, reqVO.getSupport())
                .betweenIfPresent(BlogCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BlogCategoryDO::getId));
    }

    default List<BlogCategoryDO> selectList(BlogCategoryExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BlogCategoryDO>()
                .eqIfPresent(BlogCategoryDO::getTitle, reqVO.getTitle())
                .eqIfPresent(BlogCategoryDO::getDescription, reqVO.getDescription())
                .eqIfPresent(BlogCategoryDO::getSupport, reqVO.getSupport())
                .betweenIfPresent(BlogCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BlogCategoryDO::getId));
    }


}
