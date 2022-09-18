package cn.iocoder.yudao.module.blog.controller.portal.tag.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* 博客标签 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PortalTagBaseVO {

    @ApiModelProperty(value = "标签轮廓颜色")
    @NotNull(message = "标签颜色不能为空")
    private String color;

    @ApiModelProperty(value = "标签名", required = true)
    @NotNull(message = "标签名不能为空")
    private String title;

}
