package cn.iocoder.yudao.module.blog.dal.mysql.blog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.BlogPageReqVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 博客 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface BlogMapper extends BaseMapperX<BlogDO> {

    default PageResult<BlogDO> selectPage(BlogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BlogDO>()
                .eqIfPresent(BlogDO::getCategoryId, reqVO.getCategoryId())
                .eqIfPresent(BlogDO::getTitle, reqVO.getTitle())
                .eqIfPresent(BlogDO::getSummary, reqVO.getSummary())
                .eqIfPresent(BlogDO::getHeaderImgType, reqVO.getHeaderImgType())
                .eqIfPresent(BlogDO::getHeaderImg, reqVO.getHeaderImg())
                .eqIfPresent(BlogDO::getHtmlContent, reqVO.getHtmlContent())
                .eqIfPresent(BlogDO::getContent, reqVO.getContent())
                .eqIfPresent(BlogDO::getStatus, reqVO.getStatus())
                .eqIfPresent(BlogDO::getComment, reqVO.getComment())
                .eqIfPresent(BlogDO::getSupport, reqVO.getSupport())
                .eqIfPresent(BlogDO::getWeight, reqVO.getWeight())
                .eqIfPresent(BlogDO::getLikes, reqVO.getLikes())
                .eqIfPresent(BlogDO::getClick, reqVO.getClick())
                .betweenIfPresent(BlogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BlogDO::getId));
    }



}
