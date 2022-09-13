package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 博客更新（发布） Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BlogPublishUpdateReqVO extends BlogPublishReqVO {

    @ApiModelProperty(value = "博客编号", required = true)
    @NotNull(message = "博客编号不能为空")
    private Long id;

}
