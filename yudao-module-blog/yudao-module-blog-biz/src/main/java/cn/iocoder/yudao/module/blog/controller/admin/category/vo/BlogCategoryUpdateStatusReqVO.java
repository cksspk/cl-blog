package cn.iocoder.yudao.module.blog.controller.admin.category.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 分类更新推荐状态 Request VO")
@Data
public class BlogCategoryUpdateStatusReqVO {

    @ApiModelProperty(value = "分类编号", required = true, example = "1024")
    @NotNull(message = "分类编号不能为空")
    private Long id;

    @ApiModelProperty(value = "分类", required = true, example = "1", notes = "见 CommonStatusEnum 枚举")
    @NotNull(message = "分类推荐不能为空")
//    @InEnum(value = CommonStatusEnum.class, message = "修改状态必须是 {value}")
    private Integer status;

}
