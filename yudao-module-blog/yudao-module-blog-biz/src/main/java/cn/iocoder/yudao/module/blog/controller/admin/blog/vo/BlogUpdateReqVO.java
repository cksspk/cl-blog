package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 博客更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BlogUpdateReqVO extends BlogBaseVO {

    @ApiModelProperty(value = "博客编号", required = true)
    @NotNull(message = "博客编号不能为空")
    private Long id;

}
