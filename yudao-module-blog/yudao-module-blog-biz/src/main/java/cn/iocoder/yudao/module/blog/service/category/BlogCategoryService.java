package cn.iocoder.yudao.module.blog.service.category;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.blog.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.category.BlogCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 分类 Service 接口
 *
 * @author 芋道源码
 */
public interface BlogCategoryService {

    /**
     * 创建分类
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createCategory(@Valid BlogCategoryCreateReqVO createReqVO);

    /**
     * 更新分类
     *
     * @param updateReqVO 更新信息
     */
    void updateCategory(@Valid BlogCategoryUpdateReqVO updateReqVO);

    /**
     * 更新分类推荐
     *
     * @param id 分类编号
     * @param status 推荐状态
     */
    void updateCategoryStatus(Long id, Integer status);

    /**
     * 删除分类
     *
     * @param id 编号
     */
    void deleteCategory(Long id);

    /**
     * 获得分类
     *
     * @param id 编号
     * @return 分类
     */
    BlogCategoryDO getCategory(Long id);

    /**
     * 获得分类列表
     *
     * @param ids 编号
     * @return 分类列表
     */
    List<BlogCategoryDO> getCategoryList(Collection<Long> ids);

    /**
     * 获得分类分页
     *
     * @param pageReqVO 分页查询
     * @return 分类分页
     */
    PageResult<BlogCategoryDO> getCategoryPage(BlogCategoryPageReqVO pageReqVO);

    /**
     * 获得分类列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 分类列表
     */
    List<BlogCategoryDO> getCategoryList(BlogCategoryExportReqVO exportReqVO);

    /**
     * 获得分类列表
     *
     * @return 博客标签列表列表
     */
    List<BlogCategoryDO> getCategorys();
}
