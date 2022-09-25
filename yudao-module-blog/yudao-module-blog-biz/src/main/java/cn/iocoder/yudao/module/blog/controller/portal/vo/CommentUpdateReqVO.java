package cn.iocoder.yudao.module.blog.controller.portal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 评论更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CommentUpdateReqVO extends CommentBaseVO {

 
    @ApiModelProperty(value = "评论编号", required = true)
    @NotNull(message = "评论编号不能为空")
    private Long id;


}
