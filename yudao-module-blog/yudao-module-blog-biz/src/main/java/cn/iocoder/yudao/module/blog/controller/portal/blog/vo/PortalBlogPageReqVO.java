package cn.iocoder.yudao.module.blog.controller.portal.blog.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.SortingField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Collection;

@ApiModel("门户 - 博客分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PortalBlogPageReqVO extends PageParam {


    @ApiModelProperty(value = "推荐", example = "1", notes = "见 CommonStatusEnum 枚举")
    private Integer support;

    @ApiModelProperty(value = "是否按热门排序")
    private Boolean click;

    @ApiModelProperty(value = "排序")
    private Collection<SortingField> sortingFields;
}
