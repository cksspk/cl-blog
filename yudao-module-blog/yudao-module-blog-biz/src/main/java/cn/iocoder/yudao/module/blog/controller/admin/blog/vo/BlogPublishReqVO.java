package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel("管理后台 - 博客（发布） Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BlogPublishReqVO extends BlogBaseVO {

    @ApiModelProperty(value = " 是否允许评论：1表示允许，0表示不允许 ")
    @NotNull(message = "评论设置不能为空")
    private Integer comment;

    @ApiModelProperty(value = "推荐")
    @NotNull(message = "推荐设置不能为空")
    private Integer support;

    @ApiModelProperty(value = "权重")
    @Range(min = 1, max = 5, message = "权重长度为{min}~{max}个字符")
    private Long weight;



}
