package cn.iocoder.yudao.module.blog.controller.admin.comment;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentPageReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentRespVO;
import cn.iocoder.yudao.module.blog.controller.admin.comment.vo.CommentUpdateReqVO;
import cn.iocoder.yudao.module.blog.convert.comment.CommentConvert;
import cn.iocoder.yudao.module.blog.dal.dataobject.comment.CommentDO;
import cn.iocoder.yudao.module.blog.service.comment.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Api(tags = "管理后台 - 评论")
@RestController
@RequestMapping("/blog/comment")
@Validated
public class CommentController {

    @Resource
    private CommentService commentService;


    @PutMapping("/update")
    @ApiOperation("更新评论")
    @PreAuthorize("@ss.hasPermission('${permissionPrefix}:update')")
    public CommonResult<Boolean> updateComment(@Valid @RequestBody CommentUpdateReqVO updateReqVO) {
        commentService.updateComment(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除评论")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('${permissionPrefix}:delete')")
    public CommonResult<Boolean> deleteComment(@RequestParam("id") Long id) {
        commentService.deleteComment(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得评论")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('${permissionPrefix}:query')")
    public CommonResult<CommentRespVO> getComment(@RequestParam("id") Long id) {
        CommentDO comment = commentService.getComment(id);
        return success(CommentConvert.INSTANCE.convert(comment));
    }

    @GetMapping("/list")
    @ApiOperation("获得评论列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('${permissionPrefix}:query')")
    public CommonResult<List<CommentRespVO>> getCommentList(@RequestParam("ids") Collection<Long> ids) {
        List<CommentDO> list = commentService.getCommentList(ids);
        return success(CommentConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得评论分页")
    @PreAuthorize("@ss.hasPermission('${permissionPrefix}:query')")
    public CommonResult<PageResult<CommentRespVO>> getCommentPage(@Valid CommentPageReqVO pageVO) {
        PageResult<CommentDO> pageResult = commentService.getCommentPage(pageVO);
        return success(CommentConvert.INSTANCE.convertPage(pageResult));
    }



}
