package cn.iocoder.yudao.module.blog.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 分类更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BlogCategoryUpdateReqVO extends BlogCategoryBaseVO {

    @ApiModelProperty(value = "分类编号", required = true)
    @NotNull(message = "分类编号不能为空")
    private Long id;

}
