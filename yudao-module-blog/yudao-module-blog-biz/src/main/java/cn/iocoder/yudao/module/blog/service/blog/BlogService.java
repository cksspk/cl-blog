package cn.iocoder.yudao.module.blog.service.blog;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 博客 Service 接口
 *
 * @author 芋道源码
 */
public interface BlogService {

    /**
     * 创建博客
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBlog(@Valid BlogCreateReqVO createReqVO);

    /**
     * 更新博客
     *
     * @param updateReqVO 更新信息
     */
    void updateBlog(@Valid BlogUpdateReqVO updateReqVO);

    /**
     * 删除博客
     *
     * @param id 编号
     */
    void deleteBlog(Long id);

    /**
     * 获得博客
     *
     * @param id 编号
     * @return 博客
     */
    BlogDO getBlog(Long id);

    /**
     * 获得博客列表
     *
     * @param ids 编号
     * @return 博客列表
     */
    List<BlogDO> getBlogList(Collection<Long> ids);

    /**
     * 获得博客分页
     *
     * @param pageReqVO 分页查询
     * @return 博客分页
     */
    PageResult<BlogDO> getBlogPage(BlogPageReqVO pageReqVO);

    /**
     * 获得博客列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 博客列表
     */
    List<BlogDO> getBlogList(BlogExportReqVO exportReqVO);

}
