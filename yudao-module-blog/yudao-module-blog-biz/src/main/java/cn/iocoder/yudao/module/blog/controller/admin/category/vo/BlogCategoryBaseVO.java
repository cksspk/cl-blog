package cn.iocoder.yudao.module.blog.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 分类 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BlogCategoryBaseVO {

    @ApiModelProperty(value = "分类名称", required = true)
    @NotNull(message = "分类名称不能为空")
    private String title;

    @ApiModelProperty(value = "描述", required = true)
    @NotNull(message = "描述不能为空")
    private String description;

    @ApiModelProperty(value = "是否推荐", required = true, example = "1")
    @NotNull(message = "是否推荐不能为空")
    private Integer support;

}
