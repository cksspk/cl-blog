package cn.iocoder.yudao.module.blog.controller.admin.tag.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 博客标签更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TagUpdateReqVO extends TagBaseVO {

    @ApiModelProperty(value = "标签编号", required = true)
    @NotNull(message = "标签编号不能为空")
    private Long id;

}
