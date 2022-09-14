package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 博客推荐 Request VO")
@Data
public class BlogCommentStatusReqVO {

    @ApiModelProperty(value = "博客号", required = true, example = "1024")
    @NotNull(message = "博客编号不能为空")
    private Long id;

    @ApiModelProperty(value = "评论状态", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    @NotNull(message = "状态不能为空")
    private Integer comment;

}
