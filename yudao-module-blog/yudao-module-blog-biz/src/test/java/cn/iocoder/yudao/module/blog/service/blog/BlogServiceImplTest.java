package cn.iocoder.yudao.module.blog.service.blog;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.BlogPageReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.BlogPublishCreateReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.BlogPublishUpdateReqVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import cn.iocoder.yudao.module.blog.dal.mysql.blog.BlogMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.cloneIgnoreId;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertPojoEquals;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.assertServiceException;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomLongId;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.randomPojo;
import static cn.iocoder.yudao.module.blog.enums.ErrorCodeConstants.BLOG_NOT_EXISTS;
import static org.junit.jupiter.api.Assertions.*;

/**
* {@link BlogServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(BlogServiceImpl.class)
public class BlogServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BlogServiceImpl blogService;

    @Resource
    private BlogMapper blogMapper;

    @Test
    public void testCreateBlog_success() {
        // 准备参数
        BlogPublishCreateReqVO reqVO = randomPojo(BlogPublishCreateReqVO.class);

        // 调用
        Long blogId = blogService.createBlog(reqVO);
        // 断言
        assertNotNull(blogId);
        // 校验记录的属性是否正确
        BlogDO blog = blogMapper.selectById(blogId);
        assertPojoEquals(reqVO, blog);
    }

    @Test
    public void testUpdateBlog_success() {
        // mock 数据
        BlogDO dbBlog = randomPojo(BlogDO.class);
        blogMapper.insert(dbBlog);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BlogPublishUpdateReqVO reqVO = randomPojo(BlogPublishUpdateReqVO.class, o -> {
            o.setId(dbBlog.getId()); // 设置更新的 ID
        });

        // 调用
        blogService.updateBlog(reqVO);
        // 校验是否更新正确
        BlogDO blog = blogMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, blog);
    }

    @Test
    public void testUpdateBlog_notExists() {
        // 准备参数
        BlogPublishUpdateReqVO reqVO = randomPojo(BlogPublishUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> blogService.updateBlog(reqVO), BLOG_NOT_EXISTS);
    }

    @Test
    public void testDeleteBlog_success() {
        // mock 数据
        BlogDO dbBlog = randomPojo(BlogDO.class);
        blogMapper.insert(dbBlog);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBlog.getId();

        // 调用
        blogService.deleteBlog(id);
       // 校验数据不存在了
       assertNull(blogMapper.selectById(id));
    }

    @Test
    public void testDeleteBlog_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> blogService.deleteBlog(id), BLOG_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBlogPage() {
       // mock 数据
       BlogDO dbBlog = randomPojo(BlogDO.class, o -> { // 等会查询到
           o.setCategoryId(null);
           o.setTitle(null);
           o.setSummary(null);
           o.setHeaderImgType(null);
           o.setHeaderImg(null);
           o.setHtmlContent(null);
           o.setContent(null);
           o.setStatus(null);
           o.setComment(null);
           o.setSupport(null);
           o.setWeight(null);
           o.setLikes(null);
           o.setClick(null);
           o.setCreateTime(null);
       });
       blogMapper.insert(dbBlog);
       // 测试 categoryId 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setCategoryId(null)));
       // 测试 title 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setTitle(null)));
       // 测试 summary 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setSummary(null)));
       // 测试 headerImgType 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setHeaderImgType(null)));
       // 测试 headerImg 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setHeaderImg(null)));
       // 测试 htmlContent 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setHtmlContent(null)));
       // 测试 content 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setContent(null)));
       // 测试 status 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setStatus(null)));
       // 测试 comment 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setComment(null)));
       // 测试 support 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setSupport(null)));
       // 测试 weight 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setWeight(null)));
       // 测试 like 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setLikes(null)));
       // 测试 click 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setClick(null)));
       // 测试 createTime 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setCreateTime(null)));
       // 准备参数
       BlogPageReqVO reqVO = new BlogPageReqVO();
       reqVO.setCategoryId(null);
       reqVO.setTitle(null);
       reqVO.setSummary(null);
       reqVO.setHeaderImgType(null);
       reqVO.setHeaderImg(null);
       reqVO.setHtmlContent(null);
       reqVO.setContent(null);
       reqVO.setStatus(null);
       reqVO.setComment(null);
       reqVO.setSupport(null);
       reqVO.setWeight(null);
       reqVO.setLikes(null);
       reqVO.setClick(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<BlogDO> pageResult = blogService.getBlogPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBlog, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBlogList() {
       // mock 数据
       BlogDO dbBlog = randomPojo(BlogDO.class, o -> { // 等会查询到
           o.setCategoryId(null);
           o.setTitle(null);
           o.setSummary(null);
           o.setHeaderImgType(null);
           o.setHeaderImg(null);
           o.setHtmlContent(null);
           o.setContent(null);
           o.setStatus(null);
           o.setComment(null);
           o.setSupport(null);
           o.setWeight(null);
           o.setLikes(null);
           o.setClick(null);
           o.setCreateTime(null);
       });
       blogMapper.insert(dbBlog);
       // 测试 categoryId 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setCategoryId(null)));
       // 测试 title 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setTitle(null)));
       // 测试 summary 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setSummary(null)));
       // 测试 headerImgType 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setHeaderImgType(null)));
       // 测试 headerImg 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setHeaderImg(null)));
       // 测试 htmlContent 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setHtmlContent(null)));
       // 测试 content 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setContent(null)));
       // 测试 status 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setStatus(null)));
       // 测试 comment 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setComment(null)));
       // 测试 support 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setSupport(null)));
       // 测试 weight 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setWeight(null)));
       // 测试 like 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setLikes(null)));
       // 测试 click 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setClick(null)));
       // 测试 createTime 不匹配
       blogMapper.insert(cloneIgnoreId(dbBlog, o -> o.setCreateTime(null)));
       // 准备参数
       BlogExportReqVO reqVO = new BlogExportReqVO();
       reqVO.setCategoryId(null);
       reqVO.setTitle(null);
       reqVO.setSummary(null);
       reqVO.setHeaderImgType(null);
       reqVO.setHeaderImg(null);
       reqVO.setHtmlContent(null);
       reqVO.setContent(null);
       reqVO.setStatus(null);
       reqVO.setComment(null);
       reqVO.setSupport(null);
       reqVO.setWeight(null);
       reqVO.setLikes(null);
       reqVO.setClick(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<BlogDO> list = blogService.getBlogList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBlog, list.get(0));
    }

}
