package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 博客 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class BlogBaseVO {

    @ApiModelProperty(value = "分类id ")
    private Long categoryId;

    @ApiModelProperty(value = " 文章标题 ")
    private String title;

    @ApiModelProperty(value = " 摘要 ")
    private String summary;

    @ApiModelProperty(value = "封面类型(1表示普通,0表示没有,2表示大图)", required = true)
    @NotNull(message = "封面类型(1表示普通,0表示没有,2表示大图)不能为空")
    private Integer headerImgType;

    @ApiModelProperty(value = " 封面图片地址 ")
    private String headerImg;

    @ApiModelProperty(value = "HTML 格式化后的内容")
    private String htmlContent;

    @ApiModelProperty(value = " 正文内容 ")
    private String content;

    @ApiModelProperty(value = " 文章状态,	    1表示已经发表,	    2表示草稿箱 ")
    private Integer status;

    @ApiModelProperty(value = " 是否允许评论,	    1表示允许,	    0表示不允许 ")
    private Integer comment;

    @ApiModelProperty(value = " 推荐 ")
    private Integer support;

    @ApiModelProperty(value = " 权重 ")
    private Long weight;

    @ApiModelProperty(value = " 点赞数 ")
    private Long likes;

    @ApiModelProperty(value = " 点击数 ")
    private Long click;

}
