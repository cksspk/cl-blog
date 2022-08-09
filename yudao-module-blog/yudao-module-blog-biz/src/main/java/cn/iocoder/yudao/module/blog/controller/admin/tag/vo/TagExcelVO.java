package cn.iocoder.yudao.module.blog.controller.admin.tag.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * 博客标签 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class TagExcelVO {

    @ExcelProperty("标签编号")
    private Long id;

    @ExcelProperty("标签轮廓颜色")
    private String color;

    @ExcelProperty("标签名")
    private String title;

    @ExcelProperty("创建时间")
    private Date createTime;

}
