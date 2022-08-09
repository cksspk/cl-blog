package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 博客 Excel 导出 Request VO", description = "参数和 BlogPageReqVO 是一致的")
@Data
public class BlogExportReqVO {

    @ApiModelProperty(value = "分类id ")
    private Long categoryId;

    @ApiModelProperty(value = " 文章标题 ")
    private String title;

    @ApiModelProperty(value = " 摘要 ")
    private String summary;

    @ApiModelProperty(value = "封面类型(1表示普通,0表示没有,2表示大图)")
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

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
