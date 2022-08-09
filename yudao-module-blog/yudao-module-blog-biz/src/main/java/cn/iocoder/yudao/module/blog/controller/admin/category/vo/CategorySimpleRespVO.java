package cn.iocoder.yudao.module.blog.controller.admin.category.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("管理后台 - 分类精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorySimpleRespVO {

    @ApiModelProperty(value = "分类编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "分类名称", required = true, example = "芋道")
    private String title;

}
