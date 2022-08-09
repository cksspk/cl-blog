package cn.iocoder.yudao.module.blog.service.blog;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.blog.convert.blog.BlogConvert;
import cn.iocoder.yudao.module.blog.dal.mysql.blog.BlogMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blog.enums.ErrorCodeConstants.*;

/**
 * 博客 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class BlogServiceImpl implements BlogService {

    @Resource
    private BlogMapper blogMapper;

    @Override
    public Long createBlog(BlogCreateReqVO createReqVO) {
        // 插入
        BlogDO blog = BlogConvert.INSTANCE.convert(createReqVO);
        blogMapper.insert(blog);
        // 返回
        return blog.getId();
    }

    @Override
    public void updateBlog(BlogUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateBlogExists(updateReqVO.getId());
        // 更新
        BlogDO updateObj = BlogConvert.INSTANCE.convert(updateReqVO);
        blogMapper.updateById(updateObj);
    }

    @Override
    public void deleteBlog(Long id) {
        // 校验存在
        this.validateBlogExists(id);
        // 删除
        blogMapper.deleteById(id);
    }

    private void validateBlogExists(Long id) {
        if (blogMapper.selectById(id) == null) {
            throw exception(BLOG_NOT_EXISTS);
        }
    }

    @Override
    public BlogDO getBlog(Long id) {
        return blogMapper.selectById(id);
    }

    @Override
    public List<BlogDO> getBlogList(Collection<Long> ids) {
        return blogMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<BlogDO> getBlogPage(BlogPageReqVO pageReqVO) {
        return blogMapper.selectPage(pageReqVO);
    }

    @Override
    public List<BlogDO> getBlogList(BlogExportReqVO exportReqVO) {
        return blogMapper.selectList(exportReqVO);
    }

}
