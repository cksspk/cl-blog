package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
* 博客 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BlogBaseVO {

    @ApiModelProperty(value = "分类id ")
    private Long categoryId;

    @ApiModelProperty(value = " 文章标题 ")
    @Length(min = 3, max = 100, message = "文章标题不能为空，且长度为{min}~{max}个字符")
    private String title;

    @ApiModelProperty(value = " 摘要 ")
    @Length(min = 10, max = 250, message = "摘要长度为{min}~{max}个字符")
    private String summary;

    @ApiModelProperty(value = "封面类型(1表示普通,0表示没有,2表示大图)", required = true)
    private Integer headerImgType;

    @ApiModelProperty(value = "封面图片地址")
    private String headerImg;

    @ApiModelProperty(value = "HTML 格式化后的内容")
    private String htmlContent;

    @ApiModelProperty(value = " 正文内容 ")
    @NotNull(message = "正文内容不能为空")
    private String content;

    @ApiModelProperty(value = " 文章状态：1表示已经发表，2表示草稿箱 ")
    @NotNull(message = "状态设置不能为空")
    private Integer status;

    @ApiModelProperty(value = " 点赞数 ")
    private Long likes;

    @ApiModelProperty(value = " 点击数 ")
    private Long click;

}
