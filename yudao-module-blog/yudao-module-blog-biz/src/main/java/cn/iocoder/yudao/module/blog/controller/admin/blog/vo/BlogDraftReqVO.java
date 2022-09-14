package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ApiModel("管理后台 - 博客（草稿） RequestBase VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BlogDraftReqVO extends BlogBaseVO {

    @ApiModelProperty(value = " 是否允许评论：1表示允许，0表示不允许 ")
    private Integer comment;

    @ApiModelProperty(value = "推荐")
    private Integer support;

    @ApiModelProperty(value = "权重")
    private Long weight;




}
