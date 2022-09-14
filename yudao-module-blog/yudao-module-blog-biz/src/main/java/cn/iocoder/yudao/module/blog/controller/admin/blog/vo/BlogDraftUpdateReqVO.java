package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 博客（草稿）更新 RequestBase VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BlogDraftUpdateReqVO extends BlogDraftReqVO {

    @ApiModelProperty(value = "博客编号", required = true)
    @NotNull(message = "博客编号不能为空")
    private Long id;

}
