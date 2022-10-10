package cn.iocoder.yudao.module.blog.controller.portal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ApiModel("管理后台 - 评论 Response VO")
@Data
@ToString(callSuper = true)
public class PortalCommentRespVO {



    @ApiModelProperty(value = "评论编号", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;


    @ApiModelProperty(value = "昵称")
    private String nickName;


    @ApiModelProperty(value = "Email地址")
    private String email;

    @ApiModelProperty(value = "html内容")
    private String htmlContent;

    @ApiModelProperty(value = "子评论数量")
    private Long subTotal;

    @ApiModelProperty(value = "一级评论编号")
    private Long rootId;

    private List<PortalCommentRespVO> subCommentList;

}
