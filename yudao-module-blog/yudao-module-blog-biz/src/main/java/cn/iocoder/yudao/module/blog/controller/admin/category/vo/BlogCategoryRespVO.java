package cn.iocoder.yudao.module.blog.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 分类 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BlogCategoryRespVO extends BlogCategoryBaseVO {

    @ApiModelProperty(value = "分类编号", required = true)
    private Long id;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
