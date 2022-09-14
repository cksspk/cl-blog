package cn.iocoder.yudao.module.blog.service.blog;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.*;
import cn.iocoder.yudao.module.blog.convert.blog.BlogConvert;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import cn.iocoder.yudao.module.blog.dal.dataobject.category.BlogCategoryDO;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagDO;
import cn.iocoder.yudao.module.blog.dal.mysql.blog.BlogMapper;
import cn.iocoder.yudao.module.blog.service.category.BlogCategoryService;
import cn.iocoder.yudao.module.blog.service.tag.TagService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.blog.enums.ErrorCodeConstants.BLOG_NOT_EXISTS;

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

    @Resource
    private BlogCategoryService categoryService;

    @Resource
    private TagService tagService;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Long createBlog(BlogPublishCreateReqVO createReqVO) {
        // 插入
        BlogDO blog = BlogConvert.INSTANCE.convert(createReqVO);
        blogMapper.insert(blog);
        tagService.updateTagMapping(blog.getId(), createReqVO.getTagTitleList());
        // 返回
        return blog.getId();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateBlog(BlogPublishUpdateReqVO reqVO) {
        // 校验存在
        this.validateBlogExists(reqVO.getId());
        // 更新
        BlogDO updateObj = BlogConvert.INSTANCE.convert(reqVO);
        tagService.updateTagMapping(updateObj.getId(), reqVO.getTagTitleList());
        blogMapper.updateById(updateObj);
    }


    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Long createBlogDraft(BlogDraftCreateReqVO reqVO) {
        // 插入
        BlogDO blog = BlogConvert.INSTANCE.convert(reqVO);
        blogMapper.insert(blog);
        tagService.updateTagMapping(blog.getId(), reqVO.getTagTitleList());
        // 返回
        return blog.getId();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void updateBlogDraft(BlogDraftUpdateReqVO reqVO) {
        // 校验存在
        this.validateBlogExists(reqVO.getId());
        // 更新
        BlogDO updateObj = BlogConvert.INSTANCE.convert(reqVO);
        tagService.updateTagMapping(updateObj.getId(), reqVO.getTagTitleList());
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
    public PageResult<BlogPageRespVO> getBlogPage(BlogPageReqVO pageReqVO) {
        PageResult<BlogDO> pageResult = blogMapper.selectPage(pageReqVO);
        if (CollUtil.isEmpty(pageResult.getList())) {
            return new PageResult<>(pageResult.getTotal());
        }

        // 获得分类 Category Map
        List<Long> categoryIds = convertList(pageResult.getList(), BlogDO::getCategoryId);
        Map<Long, BlogCategoryDO> categoryMap = categoryService.getCategoryMapByIds(categoryIds);
        // 转换返回
        return BlogConvert.INSTANCE.convertPage(pageResult, categoryMap);
    }

    @Override
    public void updateBlogSupport(Long id, Integer support) {
        checkBlogExists(id);
        BlogDO blogDO = new BlogDO().setId(id).setSupport(support);
        blogMapper.updateById(blogDO);
    }

    @Override
    public void updateCommentSupport(Long id, Integer comment) {
        checkBlogExists(id);
        BlogDO blogDO = new BlogDO().setId(id).setComment(comment);
        blogMapper.updateById(blogDO);
    }

    @Override
    public BlogRespVO getBlogWithTag(Long id) {
        BlogDO blogDO = blogMapper.selectById(id);
        // 找到关联标签列表
        List<TagDO> tagListByBlogId = tagService.getTagListByBlogId(id);

        return BlogConvert.INSTANCE.convert(blogDO, tagListByBlogId);
    }

    private void checkBlogExists(Long id) {
        if (id == null) {
            return;
        }
        BlogDO user = blogMapper.selectById(id);
        if (user == null) {
            throw exception(BLOG_NOT_EXISTS);
        }
    }
}
