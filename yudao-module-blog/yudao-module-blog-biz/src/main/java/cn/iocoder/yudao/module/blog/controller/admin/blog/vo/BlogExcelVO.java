package cn.iocoder.yudao.module.blog.controller.admin.blog.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 博客 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class BlogExcelVO {

    @ExcelProperty("博客编号")
    private Long id;

    @ExcelProperty("分类id ")
    private Long categoryId;

    @ExcelProperty(" 文章标题 ")
    private String title;

    @ExcelProperty(" 摘要 ")
    private String summary;

    @ExcelProperty("封面类型(1表示普通,0表示没有,2表示大图)")
    private Integer headerImgType;

    @ExcelProperty(" 封面图片地址 ")
    private String headerImg;

    @ExcelProperty("HTML 格式化后的内容")
    private String htmlContent;

    @ExcelProperty(" 正文内容 ")
    private String content;

    @ExcelProperty(" 文章状态,	    1表示已经发表,	    2表示草稿箱 ")
    private Integer status;

    @ExcelProperty(" 是否允许评论,	    1表示允许,	    0表示不允许 ")
    private Integer comment;

    @ExcelProperty(" 推荐 ")
    private Integer support;

    @ExcelProperty(" 权重 ")
    private Long weight;

    @ExcelProperty(" 点赞数 ")
    private Long like;

    @ExcelProperty(" 点击数 ")
    private Long click;

    @ExcelProperty("创建时间")
    private Date createTime;

}
