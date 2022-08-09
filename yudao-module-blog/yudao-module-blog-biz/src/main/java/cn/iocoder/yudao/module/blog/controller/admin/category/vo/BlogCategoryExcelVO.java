package cn.iocoder.yudao.module.blog.controller.admin.category.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;


/**
 * 分类 Excel VO
 *
 * @author 芋道源码
 */
@Data
public class BlogCategoryExcelVO {

    @ExcelProperty("分类编号")
    private Long id;

    @ExcelProperty("分类名称")
    private String title;

    @ExcelProperty("描述")
    private String description;

    @ExcelProperty(value = "是否推荐", converter = DictConvert.class)
    @DictFormat("common_status") // TODO 代码优化：建议设置到对应的 XXXDictTypeConstants 枚举类中
    private Integer support;

    @ExcelProperty("创建时间")
    private Date createTime;

}
