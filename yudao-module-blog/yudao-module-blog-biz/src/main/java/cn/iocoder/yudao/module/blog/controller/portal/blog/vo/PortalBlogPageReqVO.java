package cn.iocoder.yudao.module.blog.controller.portal.blog.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel("门户 - 博客分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PortalBlogPageReqVO extends PageParam {


    @ApiModelProperty(value = "推荐", example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer support;

    @ApiModelProperty(value = "是否按热门排序倒叙")
    private Boolean clickDesc;

    @ApiModelProperty(value = "分类编号", example = "1")
    private Integer categoryId;

    // TODO 最对评论 查询条件：等待评论模块


    @ApiModelProperty(value = "起始时间", example = "2022-09-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @ApiModelProperty(value = "结束时间", example = "2022-09-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}
