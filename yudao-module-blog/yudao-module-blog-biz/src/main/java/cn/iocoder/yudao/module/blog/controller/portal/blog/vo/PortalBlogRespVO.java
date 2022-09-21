package cn.iocoder.yudao.module.blog.controller.portal.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;

@ApiModel("门户 - 博客 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PortalBlogRespVO extends PortalBlogBaseVO {

    @ApiModelProperty(value = "博客编号", required = true)
    private Long id;

    @ApiModelProperty(value = " 是否允许评论：0表示允许，1表示不允许 ")
    private Integer comment;

    @ApiModelProperty(value = "推荐")
    private Integer support;

    @ApiModelProperty(value = "权重")
    private Long weight;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;


    @ApiModelProperty(value = "评论数量")
    private Long commentCount;


}
