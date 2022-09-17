package cn.iocoder.yudao.module.blog.controller.portal.blog;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.blog.controller.portal.blog.vo.PortalBlogRespVO;
import cn.iocoder.yudao.module.blog.service.blog.BlogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Api(tags = "门户 - 博客")
@RestController
@RequestMapping("/f/blog")
@Validated
@Slf4j
public class PortalBlogController {

    @Resource
    private BlogService blogService;


    @GetMapping("/support")
    @ApiOperation(value = "推荐阅读", notes = "首页右侧栏目， 加入缓存")
    public CommonResult<List<PortalBlogRespVO>> getBlogSupport() {
        List<PortalBlogRespVO> blogSupport = blogService.getBlogSupport();
        return success(blogSupport);
    }

    @GetMapping("/hot")
    @ApiOperation(value = "热门阅读", notes = "首页右侧栏目， 加入缓存")
    public CommonResult<List<PortalBlogRespVO>> getBlogHot() {
        List<PortalBlogRespVO> blogSupport = blogService.getBlogHot();
        return success(blogSupport);
    }


}
