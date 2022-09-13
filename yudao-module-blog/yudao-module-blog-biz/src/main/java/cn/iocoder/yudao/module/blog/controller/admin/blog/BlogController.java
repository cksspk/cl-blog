package cn.iocoder.yudao.module.blog.controller.admin.blog;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.*;
import cn.iocoder.yudao.module.blog.convert.blog.BlogConvert;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import cn.iocoder.yudao.module.blog.service.blog.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Api(tags = "管理后台 - 博客")
@RestController
@RequestMapping("/blog/blog")
@Validated
@Slf4j
public class BlogController {

    @Resource
    private BlogService blogService;

    @PostMapping("/create")
    @ApiOperation("创建博客（发布）")
    @PreAuthorize("@ss.hasPermission('blog:blog:create')")
    public CommonResult<Long> createBlog(@Valid @RequestBody BlogPublishCreateReqVO createReqVO) {
        return success(blogService.createBlog(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新博客（发布）")
    @PreAuthorize("@ss.hasPermission('blog:blog:update')")
    public CommonResult<Boolean> updateBlog(@Valid @RequestBody BlogPublishUpdateReqVO updateReqVO) {
        blogService.updateBlog(updateReqVO);
        return success(true);
    }


    @PreAuthorize("@ss.hasPermission('blog:blog:draftadd')")
    @ApiOperation(value = "创建博客（草稿）")
    @PostMapping("/draft/create")
    public CommonResult draft(@Valid @RequestBody BlogDraftCreateReqVO createReqVO) {
        return success(blogService.createBlogDraft(createReqVO));
    }

    @PutMapping("/draft/update")
    @ApiOperation("更新博客（草稿）")
    @PreAuthorize("@ss.hasPermission('blog:blog:draftupdate')")
    public CommonResult<Boolean> draftBlog(@Valid @RequestBody BlogDraftUpdateReqVO updateReqVO) {
        blogService.updateBlogDraft(updateReqVO);
        return success(true);
    }


    @PutMapping("/support")
    @ApiOperation("更新博客推荐状态")
    @PreAuthorize("@ss.hasPermission('blog:blog:support')")
    public CommonResult<Boolean> support(@Valid @RequestBody BlogSupportStatusReqVO reqVO) {
        blogService.updateBlogSupport(reqVO.getId(), reqVO.getSupport());
        return success(true);
    }




    @DeleteMapping("/delete")
    @ApiOperation("删除博客")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blog:blog:delete')")
    public CommonResult<Boolean> deleteBlog(@RequestParam("id") Long id) {
        blogService.deleteBlog(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得博客")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blog:blog:query')")
    public CommonResult<BlogRespVO> getBlog(@RequestParam("id") Long id) {
        BlogDO blog = blogService.getBlog(id);
        return success(BlogConvert.INSTANCE.convert(blog));
    }

    @GetMapping("/list")
    @ApiOperation("获得博客列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('blog:blog:query')")
    public CommonResult<List<BlogRespVO>> getBlogList(@RequestParam("ids") Collection<Long> ids) {

        List<BlogDO> list = blogService.getBlogList(ids);
        return success(BlogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得博客分页")
    @PreAuthorize("@ss.hasPermission('blog:blog:query')")
    public CommonResult<PageResult<BlogRespVO>> getBlogPage(@Valid BlogPageReqVO pageVO) {
        log.info("trance TEST");
        PageResult<BlogDO> pageResult = blogService.getBlogPage(pageVO);
        return success(BlogConvert.INSTANCE.convertPage(pageResult));
    }


}
