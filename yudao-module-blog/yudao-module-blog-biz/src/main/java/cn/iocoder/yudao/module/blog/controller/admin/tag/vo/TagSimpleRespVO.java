package cn.iocoder.yudao.module.blog.controller.admin.tag.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("管理后台 - 博客标签精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TagSimpleRespVO {

    @ApiModelProperty(value = "博客标签编号", required = true, example = "1024")
    private Long id;

    @ApiModelProperty(value = "博客标签名称", required = true, example = "芋道")
    private String title;

}
