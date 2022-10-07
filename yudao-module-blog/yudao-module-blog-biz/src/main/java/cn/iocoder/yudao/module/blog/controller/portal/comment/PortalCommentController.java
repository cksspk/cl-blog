package cn.iocoder.yudao.module.blog.controller.portal.comment;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentCreateReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.vo.PortalCommentPageReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.vo.PortalCommentRespVO;
import cn.iocoder.yudao.module.blog.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Api(tags = "门户 - 评论")
@RestController
@RequestMapping("/comment")
@Validated
public class PortalCommentController {

    @Resource
    private CommentService commentService;


    @GetMapping("/page")
    @ApiOperation("根据博客编号获得评论列表")
    public CommonResult<PageResult<PortalCommentRespVO>> getCommentList(PortalCommentPageReqVO reqVO) {

        return success(commentService.getCommentPageByBlogId(reqVO));
    }


    @PostMapping("/create")
    @ApiOperation("创建评论")
    public CommonResult<Long> createComment(@Valid @RequestBody CommentCreateReqVO createReqVO) {
        return success(commentService.createComment(createReqVO));
    }




}
