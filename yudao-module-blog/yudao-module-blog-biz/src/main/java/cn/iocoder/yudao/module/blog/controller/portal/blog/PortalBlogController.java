package cn.iocoder.yudao.module.blog.controller.portal.blog;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.portal.blog.vo.PortalBlogPageReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.blog.vo.PortalBlogPageRespVO;
import cn.iocoder.yudao.module.blog.controller.portal.blog.vo.PortalBlogRespVO;
import cn.iocoder.yudao.module.blog.service.blog.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Api(tags = "门户 - 博客")
@RestController
@RequestMapping("/blog")
@Validated
@Slf4j
public class PortalBlogController {

    @Resource
    private BlogService blogService;


    @GetMapping("/page")
    @ApiOperation(value = "博客分页", notes = "中加部分")
    public CommonResult<PageResult<PortalBlogPageRespVO>> getBlogPage(@Valid PortalBlogPageReqVO reqVO) {
        return success(blogService.getPortalBlogPage(reqVO));
    }

    @GetMapping("/support")
    @ApiOperation(value = "推荐阅读", notes = "首页右侧栏目")
    public CommonResult<List<PortalBlogRespVO>> getBlogSupport() {
        List<PortalBlogRespVO> blogSupport = blogService.getBlogSupport();
        return success(blogSupport);
    }

    @GetMapping("/hot")
    @ApiOperation(value = "热门阅读", notes = "首页右侧栏目")
    public CommonResult<List<PortalBlogRespVO>> getBlogHot() {
        List<PortalBlogRespVO> blogSupport = blogService.getBlogHot();
        return success(blogSupport);
    }


    @GetMapping("/get/{id}")
    @ApiOperation("获得博客")
    public CommonResult<PortalBlogRespVO> getBlog(@PathVariable("id") Long id) {
        return success(blogService.getPortalBlog(id));
    }


}
