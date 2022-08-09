package cn.iocoder.yudao.module.blog.service.category;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.blog.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.category.BlogCategoryDO;
import cn.iocoder.yudao.module.blog.dal.mysql.category.BlogCategoryMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.blog.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link BlogCategoryServiceImpl} 的单元测试类
*
* @author 芋道源码
*/
@Import(BlogCategoryServiceImpl.class)
public class BlogCategoryServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BlogCategoryServiceImpl categoryService;

    @Resource
    private BlogCategoryMapper categoryMapper;

    @Test
    public void testCreateCategory_success() {
        // 准备参数
        BlogCategoryCreateReqVO reqVO = randomPojo(BlogCategoryCreateReqVO.class);

        // 调用
        Long categoryId = categoryService.createCategory(reqVO);
        // 断言
        assertNotNull(categoryId);
        // 校验记录的属性是否正确
        BlogCategoryDO category = categoryMapper.selectById(categoryId);
        assertPojoEquals(reqVO, category);
    }

    @Test
    public void testUpdateCategory_success() {
        // mock 数据
        BlogCategoryDO dbCategory = randomPojo(BlogCategoryDO.class);
        categoryMapper.insert(dbCategory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BlogCategoryUpdateReqVO reqVO = randomPojo(BlogCategoryUpdateReqVO.class, o -> {
            o.setId(dbCategory.getId()); // 设置更新的 ID
        });

        // 调用
        categoryService.updateCategory(reqVO);
        // 校验是否更新正确
        BlogCategoryDO category = categoryMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, category);
    }

    @Test
    public void testUpdateCategory_notExists() {
        // 准备参数
        BlogCategoryUpdateReqVO reqVO = randomPojo(BlogCategoryUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> categoryService.updateCategory(reqVO), CATEGORY_NOT_EXISTS);
    }

    @Test
    public void testDeleteCategory_success() {
        // mock 数据
        BlogCategoryDO dbCategory = randomPojo(BlogCategoryDO.class);
        categoryMapper.insert(dbCategory);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbCategory.getId();

        // 调用
        categoryService.deleteCategory(id);
       // 校验数据不存在了
       assertNull(categoryMapper.selectById(id));
    }

    @Test
    public void testDeleteCategory_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> categoryService.deleteCategory(id), CATEGORY_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCategoryPage() {
       // mock 数据
       BlogCategoryDO dbCategory = randomPojo(BlogCategoryDO.class, o -> { // 等会查询到
           o.setTitle(null);
           o.setDescription(null);
           o.setSupport(null);
           o.setCreateTime(null);
       });
       categoryMapper.insert(dbCategory);
       // 测试 title 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setTitle(null)));
       // 测试 description 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setDescription(null)));
       // 测试 support 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setSupport(null)));
       // 测试 createTime 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setCreateTime(null)));
       // 准备参数
       BlogCategoryPageReqVO reqVO = new BlogCategoryPageReqVO();
       reqVO.setTitle(null);
       reqVO.setDescription(null);
       reqVO.setSupport(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<BlogCategoryDO> pageResult = categoryService.getCategoryPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbCategory, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetCategoryList() {
       // mock 数据
       BlogCategoryDO dbCategory = randomPojo(BlogCategoryDO.class, o -> { // 等会查询到
           o.setTitle(null);
           o.setDescription(null);
           o.setSupport(null);
           o.setCreateTime(null);
       });
       categoryMapper.insert(dbCategory);
       // 测试 title 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setTitle(null)));
       // 测试 description 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setDescription(null)));
       // 测试 support 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setSupport(null)));
       // 测试 createTime 不匹配
       categoryMapper.insert(cloneIgnoreId(dbCategory, o -> o.setCreateTime(null)));
       // 准备参数
       BlogCategoryExportReqVO reqVO = new BlogCategoryExportReqVO();
       reqVO.setTitle(null);
       reqVO.setDescription(null);
       reqVO.setSupport(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<BlogCategoryDO> list = categoryService.getCategoryList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbCategory, list.get(0));
    }

}
