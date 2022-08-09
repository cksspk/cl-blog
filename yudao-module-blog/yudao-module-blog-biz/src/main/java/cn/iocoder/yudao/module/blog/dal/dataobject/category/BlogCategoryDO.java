package cn.iocoder.yudao.module.blog.dal.dataobject.category;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 分类 DO
 *
 * @author 芋道源码
 */
@TableName("blog_category")
@KeySequence("blog_category_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogCategoryDO extends BaseDO {

    /**
     * 分类编号
     */
    @TableId
    private Long id;
    /**
     * 分类名称
     */
    private String title;
    /**
     * 描述
     */
    private String description;
    /**
     * 是否推荐
     *
     * 枚举 {@link TODO common_status 对应的类}
     */
    private Integer support;

}
