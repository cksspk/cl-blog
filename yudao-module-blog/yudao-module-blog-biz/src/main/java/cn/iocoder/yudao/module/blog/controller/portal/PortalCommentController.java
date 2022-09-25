package cn.iocoder.yudao.module.blog.controller.portal;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.portal.vo.PortalCommentPageReqVO;
import cn.iocoder.yudao.module.blog.controller.portal.vo.PortalCommentRespVO;
import cn.iocoder.yudao.module.blog.convert.comment.CommentConvert;
import cn.iocoder.yudao.module.blog.dal.dataobject.comment.CommentDO;
import cn.iocoder.yudao.module.blog.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Api(tags = "管理后台 - 评论")
@RestController
@RequestMapping("/comment")
@Validated
public class PortalCommentController {

    @Resource
    private CommentService commentService;


    @GetMapping("/page")
    @ApiOperation("根据博客编号获得评论列表")
    public CommonResult<PageResult<PortalCommentRespVO>> getCommentList(PortalCommentPageReqVO reqVO) {
        PageResult<CommentDO> page = commentService.getCommentPageByBlogId(reqVO);
        return success(CommentConvert.INSTANCE.convertPortalPage(page));
    }





}
