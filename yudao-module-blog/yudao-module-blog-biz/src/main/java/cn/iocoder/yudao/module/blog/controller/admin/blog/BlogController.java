package cn.iocoder.yudao.module.blog.controller.admin.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.blog.controller.admin.blog.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.blog.BlogDO;
import cn.iocoder.yudao.module.blog.convert.blog.BlogConvert;
import cn.iocoder.yudao.module.blog.service.blog.BlogService;

@Api(tags = "管理后台 - 博客")
@RestController
@RequestMapping("/blog/blog")
@Validated
@Slf4j
public class BlogController {

    @Resource
    private BlogService blogService;

    @PostMapping("/create")
    @ApiOperation("创建博客")
    @PreAuthorize("@ss.hasPermission('blog:blog:create')")
    public CommonResult<Long> createBlog(@Valid @RequestBody BlogCreateReqVO createReqVO) {
        return success(blogService.createBlog(createReqVO));
    }

    @PreAuthorize("@ss.hasPermission('blog:blog:add')")
    @ApiOperation(value = "创建博客草稿")
    @PostMapping("draft")
    public CommonResult draft(@Valid @RequestBody BlogCreateReqVO createReqVO) {
        // TODO 设置草稿状态
        createReqVO.setStatus(-1);
        return success(blogService.createBlog(createReqVO));
    }


    @PutMapping("/draft")
    @ApiOperation("更新博客")
    @PreAuthorize("@ss.hasPermission('blog:blog:update')")
    public CommonResult<Boolean> draftBlog(@Valid @RequestBody BlogUpdateReqVO updateReqVO) {
        blogService.updateBlog(updateReqVO);
        return success(true);
    }

    @PutMapping("/update")
    @ApiOperation("更新博客")
    @PreAuthorize("@ss.hasPermission('blog:blog:update')")
    public CommonResult<Boolean> updateBlog(@Valid @RequestBody BlogUpdateReqVO updateReqVO) {
        blogService.updateBlog(updateReqVO);
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

    @GetMapping("/export-excel")
    @ApiOperation("导出博客 Excel")
    @PreAuthorize("@ss.hasPermission('blog:blog:export')")
    @OperateLog(type = EXPORT)
    public void exportBlogExcel(@Valid BlogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BlogDO> list = blogService.getBlogList(exportReqVO);
        // 导出 Excel
        List<BlogExcelVO> datas = BlogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "博客.xls", "数据", BlogExcelVO.class, datas);
    }

}
