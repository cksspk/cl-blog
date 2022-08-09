package cn.iocoder.yudao.module.blog.service.category;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.blog.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.category.BlogCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.blog.convert.category.BlogCategoryConvert;
import cn.iocoder.yudao.module.blog.dal.mysql.category.BlogCategoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blog.enums.ErrorCodeConstants.*;

/**
 * 分类 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BlogCategoryServiceImpl implements BlogCategoryService {

    @Resource
    private BlogCategoryMapper blogCategoryMapper;

    @Override
    public Long createCategory(BlogCategoryCreateReqVO createReqVO) {
        // 插入
        BlogCategoryDO category = BlogCategoryConvert.INSTANCE.convert(createReqVO);
        blogCategoryMapper.insert(category);
        // 返回
        return category.getId();
    }

    @Override
    public void updateCategory(BlogCategoryUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateCategoryExists(updateReqVO.getId());
        // 更新
        BlogCategoryDO updateObj = BlogCategoryConvert.INSTANCE.convert(updateReqVO);
        blogCategoryMapper.updateById(updateObj);
    }

    @Override
    public void updateCategoryStatus(Long id, Integer status) {
        // 校验是否可以更新
        this.validateCategoryExists(id);
        // 更新状态
        BlogCategoryDO updateObject = new BlogCategoryDO();
        updateObject.setId(id);
        updateObject.setSupport(status);
        blogCategoryMapper.updateById(updateObject);
    }

    @Override
    public void deleteCategory(Long id) {
        // 校验存在
        this.validateCategoryExists(id);
        // 删除
        blogCategoryMapper.deleteById(id);
    }

    private void validateCategoryExists(Long id) {
        if (blogCategoryMapper.selectById(id) == null) {
            throw exception(CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public BlogCategoryDO getCategory(Long id) {
        return blogCategoryMapper.selectById(id);
    }

    @Override
    public List<BlogCategoryDO> getCategoryList(Collection<Long> ids) {
        return blogCategoryMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BlogCategoryDO> getCategoryPage(BlogCategoryPageReqVO pageReqVO) {
        return blogCategoryMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BlogCategoryDO> getCategoryList(BlogCategoryExportReqVO exportReqVO) {
        return blogCategoryMapper.selectList(exportReqVO);
    }

    @Override
    public List<BlogCategoryDO> getCategorys() {
        return blogCategoryMapper.selectList();
    }
}
