package cn.iocoder.yudao.module.blog.dal.mysql.blog;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.BlogPageReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.blog.vo.PortalBlogPageReqVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import cn.iocoder.yudao.module.blog.enums.blog.StatusEnums;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

import static cn.iocoder.yudao.module.blog.enums.blog.PortalBlogConstant.PORTAL_SIZE;

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


    //============== portal

    default PageResult<BlogDO> selectPortalPage(PortalBlogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BlogDO>()
                .eq(BlogDO::getStatus, StatusEnums.PUBLISH.getStatus())      // 基本条件：发布状态
                .eqIfPresent(BlogDO::getSupport, CommonStatusEnum.ENABLE.getStatus()) // 推荐

                .orderByDesc(BlogDO::getId)
                , reqVO.getSortingFields());
    }
    default List<BlogDO> selectPortalSupportList() {
        return selectList(new LambdaQueryWrapperX<BlogDO>()
                .eq(BlogDO::getStatus, StatusEnums.PUBLISH.getStatus())      // 基本条件：发布状态
                .eqIfPresent(BlogDO::getSupport, CommonStatusEnum.ENABLE.getStatus()) // 推荐

                .orderByDesc(BlogDO::getId)
                .last(PORTAL_SIZE));
    }

    default List<BlogDO> selectPortalHotList() {
        return selectList(new LambdaQueryWrapperX<BlogDO>()
                .eq(BlogDO::getStatus, StatusEnums.PUBLISH.getStatus())      // 基本条件：发布状态

                .orderByDesc(BlogDO::getClick)          // 热门，点击倒叙
                .orderByDesc(BlogDO::getId)
                .last(PORTAL_SIZE));
    }



}
