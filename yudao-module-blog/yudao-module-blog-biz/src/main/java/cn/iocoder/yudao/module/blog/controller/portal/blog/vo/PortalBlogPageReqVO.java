package cn.iocoder.yudao.module.blog.controller.portal.blog.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("门户 - 博客分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PortalBlogPageReqVO extends PageParam {

    @ApiModelProperty(value = "推荐", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer support;

    @ApiModelProperty(value = " 文章状态，1表示已经发表，2表示草稿箱")
    private Integer status;

    @ApiModelProperty(value = "是否按热门排序")
    private Boolean click;
}
